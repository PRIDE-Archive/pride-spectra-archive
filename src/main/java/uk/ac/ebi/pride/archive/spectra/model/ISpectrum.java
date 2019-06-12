package uk.ac.ebi.pride.archive.spectra.model;

import uk.ac.ebi.pride.archive.dataprovider.param.CvParamProvider;

import java.util.List;

public interface ISpectrum {


    /** Unified Spectra Identifier **/
    String getUSI();

    double[] getSpectrumMasses();

    double[] getSpectrumIntensities();

    int getMsLevel();

    List<CvParamProvider> getAdditional();

    /** Mass spectrum capture mode CENTROID or PROFILE **/
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

    /** Retention time or scan start time **/
    double getRetentionTime();

    /** precursor charge **/
    int getPrecursorCharge();

    /** Precursor mz value **/
    double getPrecursorMz();
}
