package uk.ac.ebi.pride.archive.spectra.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import uk.ac.ebi.pride.archive.dataprovider.param.CvParamProvider;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
public class CvParam implements CvParamProvider {

    String cvLabel;
    String accession;
    String name;
    String value;

    public CvParam() {
    }

    public CvParam(String cvLabel, String accession, String name, String value) {
        this.cvLabel = cvLabel;
        this.accession = accession;
        this.name = name;
        this.value = value;
    }
}