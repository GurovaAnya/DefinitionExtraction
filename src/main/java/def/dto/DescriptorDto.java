package def.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class DescriptorDto extends AbstractDto {

    private String content;
    private Integer text;
    private List<DefinitionDescriptorConnectionDto> usedInDefinitions = new ArrayList<>();
    private Long startOffset;
    private Long endOffset;
    private List<DefinitionDto> definitions = new ArrayList<>();

    public DescriptorDto() {
    }

    public DescriptorDto(String content, Integer text, Long startOffset, Long endOffset) {
        this.content = content;
        this.text = text;
        this.startOffset = startOffset;
        this.endOffset = endOffset;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getText() {
        return text;
    }

    public void setText(Integer text) {
        this.text = text;
    }

    public Long getStartOffset() {
        return startOffset;
    }

    public void setStartOffset(Long startOffset) {
        this.startOffset = startOffset;
    }

    public Long getEndOffset() {
        return endOffset;
    }

    public void setEndOffset(Long endOffset) {
        this.endOffset = endOffset;
    }

    public List<DefinitionDescriptorConnectionDto> getUsedInDefinitions() {
        return usedInDefinitions;
    }

    public void setUsedInDefinitions(List<DefinitionDescriptorConnectionDto> usedInDefinitions) {
        this.usedInDefinitions = usedInDefinitions;
    }

    public List<DefinitionDto> getDefinitions() {
        return definitions;
    }

    public void setDefinitions(List<DefinitionDto> definitions) {
        this.definitions = definitions;
    }

    @Override
    public String toString() {
        return content;
    }
}
