package def.models;

import javax.persistence.*;

@Entity(name = "DefinitionDescriptorConnection")
@Table(name = "dd_connection")
public class DefinitionDescriptorConnection{

    @Id
    @SequenceGenerator(
            name = "SQ_dd_connection_id",
            sequenceName = "SQ_dd_connection_id",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SQ_dd_connection_id"
    )
    @Column(name = "id",
            updatable = false)
    private Integer id;

    @Column(
            name = "offset_start",
            nullable = false)
    private int offsetStart;

    @Column(name = "length")
    private int length;

    @ManyToOne
    @JoinColumn(
            name = "descriptor_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "fk_descriptor_ddconnection_id"),
            nullable = false
    )
    private Descriptor descriptor;

    @ManyToOne
    @JoinColumn(
            name = "definition_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "fk_definition_ddconnection_id"),
            nullable = false
    )
    private Definition definition;

    public DefinitionDescriptorConnection(int offsetStart, int length, Descriptor descriptor, Definition definition) {
        this.offsetStart = offsetStart;
        this.length = length;
        this.descriptor = descriptor;
        this.definition = definition;
    }

    public DefinitionDescriptorConnection() { }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Descriptor getDescriptor() {
        return descriptor;
    }

    public void setDescriptor(Descriptor descriptor) {
        this.descriptor = descriptor;
    }

    public Definition getDefinition() {
        return definition;
    }

    public void setDefinition(Definition definition) {
        this.definition = definition;
    }
}
