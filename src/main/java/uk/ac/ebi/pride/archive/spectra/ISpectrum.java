package uk.ac.ebi.pride.archive.spectra;

import uk.ac.ebi.pride.archive.dataprovider.param.CvParamProvider;

import java.util.List;

public interface ISpectrum {

    double[] getMasses();

    double[] getIntensities();

    int getMsLevel();

    List<CvParamProvider> getAdditional();

    /** Mass psectrum capture mode CENTROID or PROFILE **/
    String getMsMode();

    /** Lowest Observed Mz Value **/
    double getLowestObservedMz();

    /** Highest Observed Mz Value **/
    double getHighestObservedMz();

    /** Base Peak Mz **/
    double getBasePeakMz();

    /** Base Peak Intensity **/
    double  getBasePeakInt();

    /** Total ion count **/
    double getTic();

    /** Rentention time or scan start time **/
    double getRt();

    /** precursor charge **/
    int getPrecursorCharge();

    /** Precursor mz value **/
    double getPrecursorMz();
}
