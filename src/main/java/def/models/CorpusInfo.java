package def.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Corpus")
@Table(name = "corpus")
public class CorpusInfo  {
    @Id
    @SequenceGenerator(
            name = "SQ_corpus_id",
            sequenceName = "SQ_corpus_id",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SQ_corpus_id"
    )
    @Column(name = "id",
            updatable = false)
    private Integer id;

    @JsonIgnore
    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            mappedBy = "corpusInfo")
    List<Text> includedTexts = new ArrayList<>();

    public CorpusInfo() {
    }

    public CorpusInfo(Integer id, List<Text> includedTexts) {
        this.id = id;
        this.includedTexts = includedTexts;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Text> getIncludedTexts() {
        return includedTexts;
    }

    public void setIncludedTexts(List<Text> includedTexts) {
        this.includedTexts = includedTexts;
    }
}
