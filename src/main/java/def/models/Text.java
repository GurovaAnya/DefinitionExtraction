package def.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Text")
@Table(name = "text")
public class Text {

    @Id
    @SequenceGenerator(
            name = "SQ_text_id",
            sequenceName = "SQ_text_id",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SQ_text_id"
    )
    @Column(name = "id",
            updatable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            mappedBy = "text")
    private List<Descriptor> descriptors = new ArrayList<>();

    @ManyToOne
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "fk_text_user_id")
    )
    private Redactor owner;

    @ManyToMany(mappedBy = "includedTexts")
    private List<CorpusInfo> corpusInfos = new ArrayList<>();

    @Column(name = "is_processed")
    public boolean isProcessed = false;

    public Text() {
    }

    public Text(Integer id, String name, List<Descriptor> descriptors, Redactor owner, List<CorpusInfo> corpusInfos) {
        this.id = id;
        this.name = name;
        this.descriptors = descriptors;
        this.owner = owner;
        this.corpusInfos = corpusInfos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Descriptor> getDescriptors() {
        return descriptors;
    }

    public void setDescriptors(List<Descriptor> descriptors) {
        this.descriptors = descriptors;
    }

    public Redactor getOwner() {
        return owner;
    }

    public void setOwner(Redactor owner) {
        this.owner = owner;
    }

    public List<CorpusInfo> getCorpusInfos() {
        return corpusInfos;
    }

    public void setCorpusInfos(List<CorpusInfo> corpusInfos) {
        this.corpusInfos = corpusInfos;
    }
}
