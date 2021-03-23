package def.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class CorpusInfoDto extends AbstractDto  {

    List<TextDto> includedTexts = new ArrayList<>();

    public CorpusInfoDto() {
    }

    public CorpusInfoDto(List<TextDto> includedTexts) {
        this.includedTexts = includedTexts;
    }

    public List<TextDto> getIncludedTexts() {
        return includedTexts;
    }

    public void setIncludedTexts(List<TextDto> includedTexts) {
        this.includedTexts = includedTexts;
    }
}
