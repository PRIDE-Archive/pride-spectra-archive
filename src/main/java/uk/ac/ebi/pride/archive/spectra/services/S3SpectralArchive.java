package uk.ac.ebi.pride.archive.spectra.services;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.ResponseTransformer;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.NoSuchKeyException;
import uk.ac.ebi.pride.archive.dataprovider.data.spectra.ArchiveSpectrum;
import uk.ac.ebi.pride.archive.dataprovider.data.spectra.BinaryArchiveSpectrum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;

/**
 * The {@link S3SpectralArchive} is an interface that only provides methods to read one IArchivePSM after another
 * This interface and the classes behind allow to read an entire file without needs to index the entire file. For
 * indexed and random access to peak list files please use the JmzReader interface.
 *
 * @author yperez
 */

@Service
@Slf4j
public class S3SpectralArchive {

    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    S3Client s3Client;

    @Value("${spectra.archive.s3.bucketname}")
    String bucketName;

//    public ArchiveSpectrum writePSM(String usi, ArchiveSpectrum psm) throws Exception {
//        if (psm.getUsi().startsWith(Constants.SPECTRUM_S3_HEADER)) {
//            String jsonStr = (new ObjectMapper()).writeValueAsString(psm);
//            PutObjectResult result = s3Client.putObject(bucketName, getKeyFromUsi(usi), jsonStr);
//            log.info(" - " + usi + "  " + "(size = " + result.getMetadata().getContentLength() + ")");
//        } else {
//            log.error("The spectrum usi should be started with prefix -- " + Constants.SPECTRUM_S3_HEADER);
//        }
//        return psm;
//    }

    public ArchiveSpectrum readPSM(String filePath, String usi) throws IOException {
        GetObjectRequest request = GetObjectRequest.builder().bucket(bucketName).key(filePath).build();
        InputStream inputStream;
        try {
            inputStream = s3Client.getObject(request, ResponseTransformer.toBytes()).asInputStream();
        } catch (NoSuchKeyException ex) {
            return null;
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
        String contentStr;
        while ((contentStr = in.readLine()) != null) {
            if (contentStr.startsWith("{\"usi\":\"" + usi + "\",")) { //{"usi":"mzspec:PRD000902:Rice_leaf_24h_phospho_test2:scan:3576:NN[UNIMOD:7]GSSIGS[UNIMOD:21]PGPGR/2"
                BinaryArchiveSpectrum binaryArchiveSpectrum = objectMapper.readValue(contentStr, BinaryArchiveSpectrum.class);
                return new ArchiveSpectrum(binaryArchiveSpectrum);
            }
        }
        return null;
    }

    public ArchiveSpectrum readZippedPSM(String filePath, String usi) throws IOException {
        GetObjectRequest request = GetObjectRequest.builder().bucket(bucketName).key(filePath).build();
        InputStream inputStream = s3Client.getObject(request, ResponseTransformer.toBytes()).asInputStream();
        final GZIPInputStream zipInputStream;
        zipInputStream = new GZIPInputStream(inputStream);
        ;
        BufferedReader in = new BufferedReader(new InputStreamReader(zipInputStream));
        String contentStr;
        while ((contentStr = in.readLine()) != null) {
            if (contentStr.startsWith("{\"usi\":\"" + usi + "\",")) { //{"usi":"mzspec:PRD000902:Rice_leaf_24h_phospho_test2:scan:3576:NN[UNIMOD:7]GSSIGS[UNIMOD:21]PGPGR/2"
                System.out.printf(contentStr);
                ArchiveSpectrum archiveSpectrum = objectMapper.readValue(contentStr, ArchiveSpectrum.class);
                return archiveSpectrum;
            }
        }
        return null;
    }

//    public void deletePSM(String usi) {
//        if (usi.contains(Constants.SPECTRUM_S3_HEADER)) {
//            if (usi.startsWith(bucketName + "/"))
//                usi = usi.replace(bucketName + "/", "");
//            s3Client.deleteObject(new DeleteObjectRequest(bucketName, getKeyFromUsi(usi)));
//        }
//    }
//
//    public static String getKeyFromUsi(String usi){
//        String [] usiParams = usi.split(":");
//        return usiParams[1]+"/"+usiParams[2]+ "/"+ DigestUtils.sha256Hex(usi);
//
//    }
//
//    private String getStringObject(S3ObjectInputStream binaryObject) throws IOException {
//        // Read the text input stream one line at a time and display each line.
//        BufferedReader reader = new BufferedReader(new InputStreamReader(binaryObject));
//        String line = null;
//        StringBuffer fullObject = new StringBuffer();
//        while ((line = reader.readLine()) != null) {
//            fullObject.append(line);
//        }
//        return fullObject.toString();
//    }
//
//    /**
//     * This method is really expensive, because it will try to retrieve all the objects from the S3
//     *
//     * @return List of all objects in the S3
//     */
//    private List<S3ObjectSummary> getBucketObjectSummaries() {
//
//        List<S3ObjectSummary> s3ObjectSummaries = new ArrayList<>();
//
//        try {
//            ListObjectsRequest listObjectsRequest = new ListObjectsRequest().withBucketName("");
//            ObjectListing objectListing;
//
//            do {
//                objectListing = s3Client.listObjects(listObjectsRequest);
//                for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) {
//                    s3ObjectSummaries.add(objectSummary);
//                }
//                listObjectsRequest.setMarker(objectListing.getNextMarker());
//            } while (objectListing.isTruncated());
//        } catch (AmazonServiceException ase) {
//            System.out.println("Caught an AmazonServiceException, " +
//                    "which means your request made it " +
//                    "to Amazon BdS3Client, but was rejected with an error response " +
//                    "for some reason.");
//            System.out.println("Error Message:    " + ase.getMessage());
//            System.out.println("HTTP Status Code: " + ase.getStatusCode());
//            System.out.println("AWS Error Code:   " + ase.getErrorCode());
//            System.out.println("Error Type:       " + ase.getErrorType());
//            System.out.println("Request ID:       " + ase.getRequestId());
//        } catch (AmazonClientException ace) {
//            System.out.println("Caught an AmazonClientException, " +
//                    "which means the client encountered " +
//                    "an internal error while trying to communicate" +
//                    " with BdS3Client, " +
//                    "such as not being able to access the network.");
//            System.out.println("Error Message: " + ace.getMessage());
//        }
//        return s3ObjectSummaries;
//    }
//
//    public List<String> getPsmsKeys() {
//        return getBucketObjectSummaries().stream().map(S3ObjectSummary::getKey).collect(Collectors.toList());
//    }
//
//    @Deprecated
//    public void deletePsms(List<String> usis) {
//
//        // Upload three sample objects.
//        ArrayList<DeleteObjectsRequest.KeyVersion> keys = new ArrayList<DeleteObjectsRequest.KeyVersion>();
//        for (int i = 0; i < usis.size(); i++) {
//            PutObjectResult putResult = s3Client.putObject(bucketName, usis.get(i),
//                    "Object number " + i + " to be deleted.");
//            // Gather the new object keys with version IDs.
//            keys.add(new DeleteObjectsRequest.KeyVersion(usis.get(i), putResult.getVersionId()));
//        }
//
//        // Delete the specified versions of the sample objects.
//        DeleteObjectsRequest multiObjectDeleteRequest = new DeleteObjectsRequest(bucketName)
//                .withKeys(keys)
//                .withQuiet(false);
//
//        // Verify that the object versions were successfully deleted.
//        DeleteObjectsResult delObjRes = s3Client.deleteObjects(multiObjectDeleteRequest);
//        int successfulDeletes = delObjRes.getDeletedObjects().size();
//        System.out.println(successfulDeletes + " objects successfully deleted");
//    }

}
