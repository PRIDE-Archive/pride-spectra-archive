package uk.ac.ebi.pride.archive.spectra.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Builder;
import lombok.Data;
import uk.ac.ebi.pride.archive.dataprovider.data.peptide.PSMProvider;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@JsonRootName("ArchivePSM")
@JsonTypeName("ArchivePSM")
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ArchivePSM implements PSMProvider {

    @JsonProperty("projectAccession")
    String projectAccession;

    @JsonProperty("assayAccession")
    String assayAccession;

    @JsonProperty("peptideSequence")
    String peptideSequence;

    @JsonProperty("spectrumFile")
    String spectrumFile;

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
    Collection<Modification> modifications;

    @JsonProperty("usi")
    String usi;

    @JsonProperty("masses")
    double[] masses;

    @JsonProperty("intensities")
    double[] intensities;

    @JsonProperty("msLevel")
    int msLevel;

    @JsonProperty("properties")
    List<CvParam> properties;

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

    @JsonProperty("isDecoy")
    Boolean isDecoy;

    public ArchivePSM() {
    }

    public ArchivePSM(String projectAccession, String assayAccession, String peptideSequence, String spectrumFile, double theoreticalMass, int missedCleavages, String sourceID, String spectrumTitle, double deltaMass, Collection<Modification> modifications, String usi, double[] masses, double[] intensities, int msLevel, List<CvParam> properties, String msMode, double lowestObservedMz, double highestObservedMz, double basePeakMz, double basePeakInt, double tic, double retentionTime, int precursorCharge, double precursorMz, Boolean isDecoy) {
        this.projectAccession = projectAccession;
        this.assayAccession = assayAccession;
        this.peptideSequence = peptideSequence;
        this.spectrumFile = spectrumFile;
        this.theoreticalMass = theoreticalMass;
        this.missedCleavages = missedCleavages;
        this.sourceID = sourceID;
        this.spectrumTitle = spectrumTitle;
        this.deltaMass = deltaMass;
        this.modifications = modifications;
        this.usi = usi;
        this.masses = masses;
        this.intensities = intensities;
        this.msLevel = msLevel;
        this.properties = properties;
        this.msMode = msMode;
        this.lowestObservedMz = lowestObservedMz;
        this.highestObservedMz = highestObservedMz;
        this.basePeakMz = basePeakMz;
        this.basePeakInt = basePeakInt;
        this.tic = tic;
        this.retentionTime = retentionTime;
        this.precursorCharge = precursorCharge;
        this.precursorMz = precursorMz;
        this.isDecoy = isDecoy;
    }

    @Override
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public Collection<? extends String> getAdditionalAttributesStrings() {
        return properties.stream().map(CvParam::getName).collect(Collectors.toList());
    }

    @Override
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public Collection<String> getModificationNames() {
        return modifications.stream().map(x -> x.getModificationCvTerm().getName()).collect(Collectors.toList());
    }

    @Override
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public int getNumberModifiedSites() {
        return modifications.size();
    }
}
