package uk.ac.ebi.pride.archive.spectra.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Builder;
import lombok.Data;
import uk.ac.ebi.pride.archive.dataprovider.data.peptide.PSMProvider;
import uk.ac.ebi.pride.archive.dataprovider.data.peptide.PeptideSequenceProvider;
import uk.ac.ebi.pride.archive.dataprovider.data.spectra.SpectrumProvider;

import java.util.*;
import java.util.stream.Collectors;

@JsonRootName("ArchiveSpectrum")
@JsonTypeName("ArchiveSpectrum")
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ArchiveSpectrum implements PSMProvider {

    @JsonProperty("usi")
    String usi;

    @JsonProperty("projectAccession")
    String projectAccession;

    @JsonProperty("assayAccession")
    String assayAccession;

    @JsonProperty("spectrumFile")
    String spectrumFile;

    @JsonProperty("sourceID")
    String sourceID;

    @JsonProperty("spectrumTitle")
    String spectrumTitle;

    @JsonProperty("masses")
    double[] masses;

    @JsonProperty("intensities")
    double[] intensities;

    @JsonProperty("numPeaks")
    int numPeaks;

    @JsonProperty("msLevel")
    int msLevel;

    @JsonProperty("precursorCharge")
    int precursorCharge;

    @JsonProperty("precursorMz")
    double precursorMz;

    @JsonProperty("retentionTime")
    double retentionTime;

    @JsonProperty("properties")
    List<CvParam> properties;

    /** Interpretation of the Spectra **/

    @JsonProperty("peptideSequence")
    String peptideSequence;

    @JsonProperty("missedCleavages")
    int missedCleavages;

    @JsonProperty("modifications")
    Collection<Modification> modifications;

    @JsonProperty("annotations")
    List<String> annotations;

    @JsonProperty("isDecoy")
    Boolean isDecoy;

    public ArchiveSpectrum() { }

    public ArchiveSpectrum(String usi, String projectAccession, String assayAccession, String spectrumFile, String sourceID, String spectrumTitle, double[] masses, double[] intensities, int numPeaks, int msLevel, int precursorCharge, double precursorMz, double retentionTime, List<CvParam> properties, String peptideSequence, int missedCleavages, Collection<Modification> modifications, List<String> annotations, Boolean isDecoy) {
        this.usi = usi;
        this.projectAccession = projectAccession;
        this.assayAccession = assayAccession;
        this.spectrumFile = spectrumFile;
        this.sourceID = sourceID;
        this.spectrumTitle = spectrumTitle;
        this.masses = masses;
        this.intensities = intensities;
        this.numPeaks = numPeaks;
        this.msLevel = msLevel;
        this.precursorCharge = precursorCharge;
        this.precursorMz = precursorMz;
        this.retentionTime = retentionTime;
        this.properties = properties;
        this.peptideSequence = peptideSequence;
        this.missedCleavages = missedCleavages;
        this.modifications = modifications;
        this.annotations = annotations;
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
