package def.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Descriptor")
@Table(name = "descriptor")
public class Descriptor {

    @Id
    @SequenceGenerator(
            name = "SQ_descriptor_id",
            sequenceName = "SQ_descriptor_id",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SQ_descriptor_id"
    )
    @Column(name = "id",
            updatable = false)
    private Integer id;

    @Column(name = "content",
            nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(
            name = "text_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "fk_descriptor_text_id")
    )
    private Text text;

    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            mappedBy = "descriptor")
    private List<DefinitionDescriptorConnection> usedInDefinitions = new ArrayList<>();

    @Column(name = "start_offset")
    private Long startOffset;

    @Column(name = "end_offset")
    private Long endOffset;

    public Descriptor() {
    }

    public Descriptor(String content, Text text, Long startOffset, Long endOffset) {
        this.content = content;
        this.text = text;
        this.startOffset = startOffset;
        this.endOffset = endOffset;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
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

    @Override
    public String toString() {
        return content;
    }
}
