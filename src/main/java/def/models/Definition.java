package def.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name = "Definition")
@Table(name = "definition")
public class Definition {

    @Id
    @SequenceGenerator(
            name = "SQ_descriptor_id",
            sequenceName = "SQ_definition_id",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SQ_definition_id"
    )
    @Column(name = "id",
            updatable = false)
    private Integer id;


    @Column
            (name = "content",
             nullable = false)
    private String content;




    @ManyToOne
    @JoinColumn(name = "descriptor_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "fk_descriptor_definition_id")
    )
    private Descriptor descriptor;


    @ManyToOne
    @JoinColumn(
            name = "text_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "fk_descriptor_text_id")
    )
    private Text text;

    @Column(name = "start_offset")
    private Long startOffset;

    @Column(name = "end_offset")
    private Long endOffset;

    @JsonIgnore
    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            mappedBy = "definition")
    private List<DefinitionDescriptorConnection> ddConnection;

    public Definition() {
    }

    public Definition(String content, Descriptor descriptor, Text text, Long startOffset, Long endOffset) {

        this.content = content;
        this.descriptor = descriptor;
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

    public Descriptor getDescriptor() {
        return descriptor;
    }

    public void setDescriptor(Descriptor descriptor) {
        this.descriptor = descriptor;
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
}
