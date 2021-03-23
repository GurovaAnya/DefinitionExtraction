package def.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TextDto extends AbstractDto {

    private String name;

    private List<DescriptorDto> descriptors = new ArrayList<>();
    private Integer owner;
    private Integer corpusInfo;
    private boolean isProcessed;

    public TextDto() {
    }

    public TextDto(String name, List<DescriptorDto> descriptors, Integer owner, Integer corpusInfo, boolean isProcessed) {
        this.name = name;
        this.descriptors = descriptors;
        this.owner = owner;
        this.corpusInfo = corpusInfo;
        this.isProcessed = isProcessed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DescriptorDto> getDescriptors() {
        return descriptors;
    }

    public void setDescriptors(List<DescriptorDto> descriptors) {
        this.descriptors = descriptors;
    }

    public Integer getOwner() {
        return owner;
    }

    public void setOwner(Integer owner) {
        this.owner = owner;
    }

    public Integer getCorpusInfo() {
        return corpusInfo;
    }

    public void setCorpusInfo(Integer corpusInfo) {
        this.corpusInfo = corpusInfo;
    }

    public boolean isProcessed() {
        return isProcessed;
    }

    public void setProcessed(boolean processed) {
        isProcessed = processed;
    }
}
