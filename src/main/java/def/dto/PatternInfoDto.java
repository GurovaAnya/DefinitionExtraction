package def.dto;

import lombok.Data;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;

@Data
public class PatternInfoDto extends AbstractDto {

    private String name;
    private String pathToFile;
    private Integer owner;

    public PatternInfoDto() {
    }

    public PatternInfoDto(String name, String pathToFile, Integer owner) {
        this.name = name;
        this.pathToFile = pathToFile;
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPathToFile() {
        return pathToFile;
    }

    public void setPathToFile(String pathToFile) {
        this.pathToFile = pathToFile;
    }

    public Integer getOwner() {
        return owner;
    }

    public void setOwner(Integer owner) {
        this.owner = owner;
    }
}
