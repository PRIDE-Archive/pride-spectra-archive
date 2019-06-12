package uk.ac.ebi.pride.archive.spectra.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import uk.ac.ebi.pride.archive.dataprovider.param.CvParamProvider;

import java.util.List;
import java.util.Map;


@JsonRootName("ArchivePSM")
@JsonTypeName("ArchivePSM")

public class ArchivePSM implements IArchivePSM {

    @JsonProperty("sequence")
    String sequence;

    @JsonProperty("theoreticalMass")
    double theoreticalMass;

    @JsonProperty("missedCleavages")
    int missedCleavages;

    @JsonProperty("sourceID")
    String sourceID;

    @JsonProperty("spectrumTitle")
    String spectrumTitle;

    @JsonProperty("deltaMass")
    double deltaMass;

    @JsonProperty("modifications")
    Map<Integer, CvParamProvider> modifications;

    @JsonProperty("usi")
    String usi;

    @JsonProperty("spectrumMasses")
    double[] spectrumMasses;

    @JsonProperty("spectrumIntensities")
    double[] spectrumIntensities;

    @JsonProperty("msLevel")
    int msLevel;

    @JsonProperty("properties")
    List<CvParamProvider> properties;

    @JsonProperty("msMode")
    String msMode;

    @JsonProperty("lowestObservedMz")
    double lowestObservedMz;

    @JsonProperty("highestObservedMz")
    double highestObservedMz;

    @JsonProperty("basePeakMz")
    double basePeakMz;

    @JsonProperty("basePeakInt")
    double basePeakInt;

    @JsonProperty("tic")
    double tic;

    @JsonProperty("retentionTime")
    double retentionTime;

    @JsonProperty("precursorCharge")
    int precursorCharge;

    @JsonProperty("precursorMz")
    double precursorMz;

    @Override
    public String getSequence() {
        return sequence;
    }

    @Override
    public double getTheoreticalMass() {
        return theoreticalMass;
    }

    @Override
    public int getMissedCleavages() {
        return missedCleavages;
    }

    @Override
    public String getSourceID() {
        return sourceID;
    }

    @Override
    public String getSpectrumTitle() {
        return spectrumTitle;
    }

    @Override
    public double getDeltaMass() {
        return deltaMass;
    }

    @Override
    public Map<Integer, CvParamProvider> getModifications() {
        return modifications;
    }

    @Override
    public String getUSI() {
        return usi;
    }

    @Override
    public double[] getSpectrumMasses() {
        return spectrumMasses;
    }

    @Override
    public double[] getSpectrumIntensities() {
        return spectrumIntensities;
    }

    @Override
    public int getMsLevel() {
        return msLevel;
    }

    @Override
    public List<CvParamProvider> getAdditional() {
        return properties;
    }

    @Override
    public String getMsMode() {
        return msMode;
    }

    @Override
    public double getLowestObservedMz() {
        return lowestObservedMz;
    }

    @Override
    public double getHighestObservedMz() {
        return highestObservedMz;
    }

    @Override
    public double getBasePeakMz() {
        return basePeakMz;
    }

    @Override
    public double getBasePeakInt() {
        return basePeakInt;
    }

    @Override
    public double getTic() {
        return tic;
    }

    @Override
    public double getRetentionTime() {
        return retentionTime;
    }

    @Override
    public int getPrecursorCharge() {
        return precursorCharge;
    }

    @Override
    public double getPrecursorMz() {
        return precursorMz;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public void setTheoreticalMass(double theoreticalMass) {
        this.theoreticalMass = theoreticalMass;
    }

    public void setMissedCleavages(int missedCleavages) {
        this.missedCleavages = missedCleavages;
    }

    public void setSourceID(String sourceID) {
        this.sourceID = sourceID;
    }

    public void setSpectrumTitle(String spectrumTitle) {
        this.spectrumTitle = spectrumTitle;
    }

    public void setDeltaMass(double deltaMass) {
        this.deltaMass = deltaMass;
    }

    public void setModifications(Map<Integer, CvParamProvider> modifications) {
        this.modifications = modifications;
    }

    public void setUsi(String usi) {
        this.usi = usi;
    }

    public void setSpectrumMasses(double[] spectrumMasses) {
        this.spectrumMasses = spectrumMasses;
    }

    public void setSpectrumIntensities(double[] spectrumIntensities) {
        this.spectrumIntensities = spectrumIntensities;
    }

    public void setMsLevel(int msLevel) {
        this.msLevel = msLevel;
    }

    public void setProperties(List<CvParamProvider> properties) {
        this.properties = properties;
    }

    public void setMsMode(String msMode) {
        this.msMode = msMode;
    }

    public void setLowestObservedMz(double lowestObservedMz) {
        this.lowestObservedMz = lowestObservedMz;
    }

    public void setHighestObservedMz(double highestObservedMz) {
        this.highestObservedMz = highestObservedMz;
    }

    public void setBasePeakMz(double basePeakMz) {
        this.basePeakMz = basePeakMz;
    }

    public void setBasePeakInt(double basePeakInt) {
        this.basePeakInt = basePeakInt;
    }

    public void setTic(double tic) {
        this.tic = tic;
    }

    public void setRetentionTime(double retentionTime) {
        this.retentionTime = retentionTime;
    }

    public void setPrecursorCharge(int precursorCharge) {
        this.precursorCharge = precursorCharge;
    }

    public void setPrecursorMz(double precursorMz) {
        this.precursorMz = precursorMz;
    }
}
