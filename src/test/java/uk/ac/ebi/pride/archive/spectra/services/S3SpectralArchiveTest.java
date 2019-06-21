package uk.ac.ebi.pride.archive.spectra.services;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import uk.ac.ebi.pride.archive.dataprovider.data.peptide.PSMProvider;
import uk.ac.ebi.pride.archive.spectra.configs.AWS3Configuration;
import uk.ac.ebi.pride.archive.spectra.model.ArchivePSM;
import uk.ac.ebi.pride.archive.spectra.model.CvParam;
import uk.ac.ebi.pride.tools.jmzreader.model.Spectrum;
import uk.ac.ebi.pride.tools.mgf_parser.MgfFile;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AWS3Configuration.class})
@Slf4j
@TestPropertySource(locations = "classpath:tes-application.properties")
public class S3SpectralArchiveTest {

    private MgfFile mgfFile = new MgfFile();
    private File sourceFile;

    @Autowired
    S3SpectralArchive  spectralArchive;

    @Before
    public void setUp() throws Exception {
        loadTestFile();
    }

    private void loadTestFile() throws Exception {
        URL testFile = getClass().getClassLoader().getResource("F001257.mgf");
        Assert.assertNotNull("Error loading mgf test file", testFile);
        sourceFile = new File(testFile.toURI());
        mgfFile = new MgfFile(sourceFile);
    }

    @Test
    public void writePSM() throws IOException {
        for(Spectrum spectrum: mgfFile.getMs2QueryIterator()){
            double[] masses = new double[spectrum.getPeakList().size()];
            double[] intensities = new double[spectrum.getPeakList().size()];
            int count = 0;
            for(Map.Entry entry: spectrum.getPeakList().entrySet()){
                masses[count] = (Double)entry.getKey();
                intensities[count] = (Double) entry.getValue();
                count++;
            }
            List<CvParam> params = new ArrayList<>();
            params.addAll(spectrum.getAdditional().getCvParams()
                            .stream()
                            .map(x-> new CvParam(x.getCv(),x.getAccession(), x.getName(),x.getValue()))
                    .collect(Collectors.toList())
            );

            ArchivePSM psm = ArchivePSM.builder()
                    .msLevel(spectrum.getMsLevel())
                    .masses(masses)
                    .intensities(intensities)
                    .precursorMz(spectrum.getPrecursorMZ())
                    .properties(params)
                    .build();
            if(spectrum.getPrecursorCharge() != null)
                psm.setPrecursorCharge(spectrum.getPrecursorCharge());
            psm.setUsi("mzspec:" + "PXEXAMPLE:" + sourceFile.getAbsolutePath() + ":indexNumber:" + spectrum.getId());
            spectralArchive.writePSM(psm.getUsi(), psm);
            log.info("Spectrum -- " + spectrum.getId());
        }
    }

    @Test
    public void getAllPSMs() {
//        for(PSMProvider spectrum: spectralArchive.getAllPSMs()){
//            log.info("Spectrum -- " + spectrum.getUsi());
//        }
    }

    @Test
    public void readPSM() throws IOException {
        PSMProvider psm = spectralArchive.readPSM("mzspec:PXD000001:PRIDE_Exp_Complete_Ac_22134.xml:scan:3782");
        log.info(psm.getUsi());
    }

    @Test
    public void deletePSM() {
        spectralArchive.deletePSM("ebi-pride/Volumes/yasset_data/QC_20140521_1.mzML");
    }

    @Test
    public void deletePSMs(){
        List<String> keys = new ArrayList<>();
        for(Spectrum spectrum: mgfFile.getMs2QueryIterator()) {
            spectralArchive.deletePSM("mzspec:" + "PXEXAMPLE:" + sourceFile.getAbsolutePath() + ":indexNumber:" + spectrum.getId());
        }
    }

    @Test
    public void getBucketObjectSummaries() {
        List<String> summary = spectralArchive.getPsmsKeys();
        log.debug(String.valueOf(summary.size()));
        for(String objectSummary: summary){
            log.info(objectSummary);
        }
    }
}