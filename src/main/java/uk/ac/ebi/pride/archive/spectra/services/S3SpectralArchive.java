package uk.ac.ebi.pride.archive.spectra.services;



import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import uk.ac.ebi.pride.archive.spectra.model.ArchivePSM;
import uk.ac.ebi.pride.archive.spectra.model.IArchivePSM;
import uk.ac.ebi.pride.archive.spectra.utils.Constants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

/**
 * The {@link S3SpectralArchive} is an interface that only provides methods to read one IArchivePSM after another
 * This interface and the classes behind allow to read an entire file without needs to index the entire file. For
 * indexed and random access to peak list files please use the JmzReader interface.
 *
 * @author yperez
 */

@Service
@Slf4j
public class S3SpectralArchive implements ISpectralArchive {

    @Autowired
    AmazonS3 s3Client;

    @Value("${spectra.archive.s3.bucketname}")
    String bucketName;

    public IArchivePSM writePSM(String usi, IArchivePSM psm) throws IOException {

        try {
            if(psm.getUSI().startsWith(Constants.SPECTRUM_S3_HEADER)){
                String jsonStr = (new ObjectMapper()).writeValueAsString(psm);
                s3Client.putObject(bucketName, usi, jsonStr);
                log.debug("Spectrum save in the S3 backut -- " + psm.getSequence());
            }else{
                log.error("The spectrum usi should be started with prefix -- " + Constants.SPECTRUM_S3_HEADER);
            }


        } catch(SdkClientException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return psm;
    }

    public IArchivePSM readPSM(String usi) throws IOException {
        S3Object fullObject = s3Client.getObject(new GetObjectRequest(bucketName, usi));
        S3ObjectInputStream content = fullObject.getObjectContent();
        ArchivePSM psm = (new ObjectMapper()).readValue(getStringObject(content), ArchivePSM.class);
        return psm;
    }

    private String getStringObject(S3ObjectInputStream binaryObject) throws IOException {
        // Read the text input stream one line at a time and display each line.
        BufferedReader reader = new BufferedReader(new InputStreamReader(binaryObject));
        String line = null;
        StringBuffer fullObject = new StringBuffer();
        while ((line = reader.readLine()) != null) {
            fullObject.append(line);
        }
        return fullObject.toString();
    }

    public List<IArchivePSM> getAllPSMs(){

        log.info("Listing objects form S3 -- ");
        ObjectListing objectListing = s3Client.listObjects(new ListObjectsRequest().withBucketName(""));
        for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) {
            System.out.println(" - " + objectSummary.getKey() + "  " +
                    "(size = " + objectSummary.getSize() + ")");
        }
        System.out.println();
        return Collections.EMPTY_LIST;

    }

}
