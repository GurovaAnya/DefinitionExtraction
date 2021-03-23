package def.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import def.models.DefinitionDescriptorConnection;
import def.models.Descriptor;
import def.models.Text;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
public class DefinitionDto extends AbstractDto {

    private String content;
    private Integer descriptor;
    private Integer text;
    private Long startOffset;
    private Long endOffset;
    private List<DefinitionDescriptorConnectionDto> ddConnection;

    public DefinitionDto(Integer id, String content, Integer descriptor, Integer text, Long startOffset, Long endOffset, List<DefinitionDescriptorConnectionDto> ddConnection) {
        this.id = id;
        this.content = content;
        this.descriptor = descriptor;
        this.text = text;
        this.startOffset = startOffset;
        this.endOffset = endOffset;
        this.ddConnection = ddConnection;
    }

    public DefinitionDto() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getDescriptor() {
        return descriptor;
    }

    public void setDescriptor(Integer descriptor) {
        this.descriptor = descriptor;
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

    public List<DefinitionDescriptorConnectionDto> getDdConnection() {
        return ddConnection;
    }

    public void setDdConnection(List<DefinitionDescriptorConnectionDto> ddConnection) {
        this.ddConnection = ddConnection;
    }
}
