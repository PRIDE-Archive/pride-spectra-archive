package uk.ac.ebi.pride.archive.spectra.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import uk.ac.ebi.pride.archive.dataprovider.common.Tuple;
import uk.ac.ebi.pride.archive.dataprovider.data.ptm.IdentifiedModificationProvider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class Modification implements IdentifiedModificationProvider {

    @JsonProperty("neutralLoss")
    CvParam neutralLoss;

    @JsonProperty("positionMap")
    List<uk.ac.ebi.pride.archive.dataprovider.common.Tuple<Integer, List<CvParam>>> positionMap;

    @JsonProperty("modificationCvTerm")
    private CvParam modificationCvTerm;

    @JsonProperty("properties")
    private List<CvParam> properties;

    @Override
    public CvParam getNeutralLoss() {
        return neutralLoss;
    }

    @Override
    public List<Tuple<Integer, List<CvParam>>> getPositionMap() {
        return positionMap;
    }

    @Override
    public CvParam getModificationCvTerm() {
        return modificationCvTerm;
    }

    @Override
    public Collection<? extends String> getAdditionalAttributesStrings() {
        if(properties != null && properties.size() > 0)
            return properties.stream().map(CvParam::getName).collect(Collectors.toList());
        return new ArrayList<>();
    }

    public Modification() {
    }

    public Modification(CvParam neutralLoss, List<Tuple<Integer, List<CvParam>>> positionMap, CvParam modificationCvTerm, List<CvParam> properties) {
        this.neutralLoss = neutralLoss;
        this.positionMap = positionMap;
        this.modificationCvTerm = modificationCvTerm;
        this.properties = properties;
    }
}
