package def.models;

import javax.persistence.*;

@Entity(name = "DefinitionDescriptorConnection")
@Table(name = "dd_connection")
public class DefinitionDescriptorConnection {

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

    @Column(name = "offset_start")
    private int offsetStart;

    @Column(name = "length")
    private int length;

    @ManyToOne
    @JoinColumn(
            name = "descriptor_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "fk_descriptor_ddconnection_id")
    )
    private Descriptor descriptor;

    @ManyToOne
    @JoinColumn(
            name = "definition_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "fk_definition_ddconnection_id")
    )
    private Definition definition;
}
