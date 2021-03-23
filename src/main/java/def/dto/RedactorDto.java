package def.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.apache.poi.ss.formula.functions.T;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
public class RedactorDto extends AbstractDto {

    private String login;
    private String name;
    private String password;
    private Date registrationDate;
    private List<TextDto> texts = new ArrayList<>();
    private List<PatternInfoDto> patternInfos = new ArrayList<>();

    public RedactorDto() {
    }

    public RedactorDto(String login, String name, String password, Date registrationDate, List<TextDto> texts, List<PatternInfoDto> patternInfos) {
        this.login = login;
        this.name = name;
        this.password = password;
        this.registrationDate = registrationDate;
        this.texts = texts;
        this.patternInfos = patternInfos;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public List<TextDto> getTexts() {
        return texts;
    }

    public void setTexts(List<TextDto> texts) {
        this.texts = texts;
    }

    public List<PatternInfoDto> getPatternInfos() {
        return patternInfos;
    }

    public void setPatternInfos(List<PatternInfoDto> patternInfos) {
        this.patternInfos = patternInfos;
    }
}
