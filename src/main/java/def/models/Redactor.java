package def.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "User")
@Table(name = "user")
public class Redactor {

    @Id
    @SequenceGenerator(
            name = "SQ_user_id",
            sequenceName = "SQ_user_id",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SQ_user_id"
    )
    @Column(name = "id",
            updatable = false)
    private Integer id;

    @Column(name = "login")
    private String login;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "registration_date",
           columnDefinition = "DATETIME")
    private String registrationDate;

    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.DETACH},
            mappedBy = "owner")
    private List<Text> texts = new ArrayList<>();

    @JsonIgnore
    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.DETACH},
            mappedBy = "owner")
    private List<PatternInfo> patternInfos = new ArrayList<>();

    public Redactor() {
    }

    public Redactor(String login, String name, String password, String registrationDate, List<Text> texts, List<PatternInfo> patternInfos) {
        this.login = login;
        this.name = name;
        this.password = password;
        this.registrationDate = registrationDate;
        this.texts = texts;
        this.patternInfos = patternInfos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public List<Text> getTexts() {
        return texts;
    }

    public void setTexts(List<Text> texts) {
        this.texts = texts;
    }

    public List<PatternInfo> getPatterns() {
        return patternInfos;
    }

    public void setPatterns(List<PatternInfo> patternInfos) {
        this.patternInfos = patternInfos;
    }
}
