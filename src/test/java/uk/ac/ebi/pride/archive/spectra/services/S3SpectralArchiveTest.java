package uk.ac.ebi.pride.archive.spectra.services;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {AWS3Configuration.class})
//@Slf4j
//@TestPropertySource(locations = "classpath:test-application.properties")
//@Deprecated
//@Ignore
public class S3SpectralArchiveTest {

//    private MgfFile mgfFile = new MgfFile();
//    private File sourceFile;
//
//    @Autowired
//    S3SpectralArchive  spectralArchive;
//
//    @Before
//    public void setUp() throws Exception {
//        loadTestFile();
//    }
//
//    private void loadTestFile() throws Exception {
//        URL testFile = getClass().getClassLoader().getResource("F001257.mgf");
//        Assert.assertNotNull("Error loading mgf test file", testFile);
//        sourceFile = new File(testFile.toURI());
//        mgfFile = new MgfFile(sourceFile);
//    }
//
//    @Test
//    @Ignore
//    public void writePSM() throws Exception {
//        for(Spectrum spectrum: mgfFile.getMs2QueryIterator()){
//            Double[] masses = new Double[spectrum.getPeakList().size()];
//            Double[] intensities = new Double[spectrum.getPeakList().size()];
//            int count = 0;
//            for(Map.Entry entry: spectrum.getPeakList().entrySet()){
//                masses[count] = (Double)entry.getKey();
//                intensities[count] = (Double) entry.getValue();
//                count++;
//            }
//            Set<Param> params = new HashSet<>();
//            params.addAll(spectrum.getAdditional().getCvParams()
//                            .stream()
//                            .map(x-> new Param(x.getName(),x.getValue()))
//                    .collect(Collectors.toList())
//            );
//
//            ArchiveSpectrum psm = ArchiveSpectrum.builder()
//                    .msLevel(spectrum.getMsLevel())
//                    .masses(masses)
//                    .intensities(intensities)
//                    .precursorMz(spectrum.getPrecursorMZ())
//                    .properties(params)
//                    .build();
//            if(spectrum.getPrecursorCharge() != null)
//                psm.setPrecursorCharge(spectrum.getPrecursorCharge());
//            psm.setUsi("mzspec:" + "PXEXAMPLE:" + sourceFile.getAbsolutePath() + ":indexNumber:" + spectrum.getId());
//            spectralArchive.writePSM(psm.getUsi(), psm);
//            log.info("Spectrum -- " + spectrum.getId());
//        }
//    }
//
//    @Test
//    public void getAllPSMs() {
////        for(PSMProvider spectrum: spectralArchive.getAllPSMs()){
////            log.info("Spectrum -- " + spectrum.getUsi());
////        }
//    }
//
//    @Test
//    @Ignore
//    public void readPSM() throws IOException {
//        PSMProvider psm = spectralArchive.readPSM("mzspec:PXD000001:PRIDE_Exp_Complete_Ac_22134.xml:scan:3782");
//        log.info(psm.getUsi());
//    }
//
//    @Test
//    public void deletePSM() {
//        spectralArchive.s3Client.deleteObject("ebi-pride","Volumes/yasset_data/QC_20140521_1.mzML");
//    }
//
//    @Test
//    public void deletePSMs(){
//        List<String> keys = new ArrayList<>();
//        for(Spectrum spectrum: mgfFile.getMs2QueryIterator()) {
//            spectralArchive.deletePSM("mzspec:" + "PXEXAMPLE:" + sourceFile.getAbsolutePath() + ":indexNumber:" + spectrum.getId());
//        }
//    }
//
//    @Test
//    public void getBucketObjectSummaries() {
//        List<String> summary = spectralArchive.getPsmsKeys();
//        log.debug(String.valueOf(summary.size()));
//        summary.parallelStream().forEach(objectSummary ->{
//            log.info(objectSummary);
//        });
//    }
//
//    @Test
//    public void numberOfObjects() {
//        List<String> summary = spectralArchive.getPsmsKeys();
//        log.info(String.valueOf(summary.size()));
//    }
//
//
//
//    @Test
//    @Ignore
//    public void deleteAllObjects() {
//        List<String> summary = spectralArchive.getPsmsKeys();
//        log.debug(String.valueOf(summary.size()));
//        summary.parallelStream().forEach(objectSummary ->{
//            spectralArchive.deletePSM(objectSummary);
//        });
//    }
}