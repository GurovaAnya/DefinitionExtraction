package def.dto;

import def.models.Definition;
import lombok.Data;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;

@Data
public class DefinitionDescriptorConnectionDto extends AbstractDto {

    private int offsetStart;
    private int length;
    private Integer descriptor;
    private Integer definition;

    public DefinitionDescriptorConnectionDto() {
    }

    public DefinitionDescriptorConnectionDto(int offsetStart, int length, Integer descriptor, Integer definition) {
        this.offsetStart = offsetStart;
        this.length = length;
        this.descriptor = descriptor;
        this.definition = definition;
    }

    public int getOffsetStart() {
        return offsetStart;
    }

    public void setOffsetStart(int offsetStart) {
        this.offsetStart = offsetStart;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Integer getDescriptor() {
        return descriptor;
    }

    public void setDescriptor(Integer descriptor) {
        this.descriptor = descriptor;
    }

    public Integer getDefinition() {
        return definition;
    }

    public void setDefinition(Integer definition) {
        this.definition = definition;
    }
}
