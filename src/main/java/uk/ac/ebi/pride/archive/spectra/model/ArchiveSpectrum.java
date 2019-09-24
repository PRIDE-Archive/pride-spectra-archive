package uk.ac.ebi.pride.archive.spectra.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Builder;
import lombok.Data;
import uk.ac.ebi.pride.archive.dataprovider.common.Tuple;
import uk.ac.ebi.pride.archive.dataprovider.data.peptide.PSMProvider;
import uk.ac.ebi.pride.archive.dataprovider.data.peptide.PeptideSequenceProvider;
import uk.ac.ebi.pride.archive.dataprovider.data.ptm.IdentifiedModificationProvider;
import uk.ac.ebi.pride.archive.dataprovider.data.spectra.SpectrumProvider;
import uk.ac.ebi.pride.archive.dataprovider.param.CvParamProvider;

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
    Double[] masses;

    @JsonProperty("intensities")
    Double[] intensities;

    @JsonProperty("numPeaks")
    Integer numPeaks;

    @JsonProperty("msLevel")
    Integer msLevel;

    @JsonProperty("precursorCharge")
    Integer precursorCharge;

    @JsonProperty("precursorMz")
    Double precursorMz;

    @JsonProperty("retentionTime")
    Double retentionTime;

    @JsonProperty("properties")
    List<CvParam> properties;

    /** Interpretation of the Spectra **/

    @JsonProperty("peptideSequence")
    String peptideSequence;

    @JsonProperty("missedCleavages")
    Integer missedCleavages;

    @JsonProperty("modifications")
    Collection<Modification> modifications;

    @JsonProperty("annotations")
    List<String> annotations;

    @JsonProperty("isDecoy")
    Boolean isDecoy;

    @JsonProperty("qualityEstimationMethods")
    private List<CvParam> qualityEstimationMethods;

    @JsonProperty("isValid")
    private Boolean isValid;

    public ArchiveSpectrum() { }

    public ArchiveSpectrum(String usi, String projectAccession, String assayAccession, String spectrumFile, String sourceID, String spectrumTitle, Double[] masses, Double[] intensities, Integer numPeaks, Integer msLevel, Integer precursorCharge, Double precursorMz, Double retentionTime, List<CvParam> properties, String peptideSequence, Integer missedCleavages, Collection<Modification> modifications, List<String> annotations, Boolean isDecoy, List<CvParam> qualityEstimationMethods, Boolean isValid) {
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
        this.qualityEstimationMethods = qualityEstimationMethods;
        this.isValid = isValid;
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
    public Integer getNumberModifiedSites() {
        return modifications.size();
    }

    @Override
    public Boolean isDecoy() {
        return isDecoy;
    }


    @Override
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public Collection<? extends CvParamProvider> getAttributes() {
        return properties;
    }

    @Override
    public Boolean isValid() {
        return isValid;
    }


}
