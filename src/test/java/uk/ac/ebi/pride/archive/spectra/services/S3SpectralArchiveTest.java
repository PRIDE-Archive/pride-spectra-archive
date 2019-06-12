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
import uk.ac.ebi.pride.archive.spectra.configs.AWS3Configuration;
import uk.ac.ebi.pride.archive.spectra.model.ArchivePSM;
import uk.ac.ebi.pride.archive.spectra.model.IArchivePSM;
import uk.ac.ebi.pride.tools.jmzreader.model.Spectrum;
import uk.ac.ebi.pride.tools.mgf_parser.MgfFile;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AWS3Configuration.class})
@Slf4j
@TestPropertySource(locations = "classpath:tes-application.properties")
public class S3SpectralArchiveTest {

    private MgfFile mgfFile = new MgfFile();
    private File sourceFile;

    @Autowired
    S3SpectralArchive spectralArchive;

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
            ArchivePSM psm = new ArchivePSM();
            psm.setMsLevel(spectrum.getMsLevel());
            if(spectrum.getPrecursorCharge() != null)
                psm.setPrecursorCharge(spectrum.getPrecursorCharge());
            psm.setUsi("mzspec:" + "PXEXAMPLE:" + sourceFile.getAbsolutePath() + ":indexNumber:" + spectrum.getId());
            spectralArchive.writePSM(psm.getUSI(), psm);
            log.info("Spectrum -- " + spectrum.getId());
        }
    }

    @Test
    public void getAllPSMs() {
        for(IArchivePSM spectrum: spectralArchive.getAllPSMs()){
            log.info("Spectrum -- " + spectrum.getSourceID());
        }
    }

    @Test
    public void readPSM() throws IOException {
        IArchivePSM psm = spectralArchive.readPSM("mzspec:PXEXAMPLE:/Users/yperez/IdeaProjects/github-repo/pride-new/pride-spectra-archive/target/test-classes/F001257.mgf:indexNumber:9");
        log.info(psm.getUSI());
    }

    @Test
    public void deletePSM() throws IOException {
        spectralArchive.deletePSM("mzspec:PXEXAMPLE:/Users/yperez/IdeaProjects/github-repo/pride-new/pride-spectra-archive/target/test-classes/F001257.mgf:indexNumber:9");
    }
}