package def.models;

import javax.persistence.*;

@Entity(name = "Pattern")
@Table(name = "pattern")
public class PatternInfo {

    @Id
    @SequenceGenerator(
            name = "SQ_pattern_id",
            sequenceName = "SQ_pattern_id",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SQ_pattern_id"
    )
    @Column(name = "id",
            updatable = false)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "path_to_file")
    private String pathToFile;

    @ManyToOne
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "fk_pattern_user_id")
    )
    private Redactor owner;

    public PatternInfo() {
    }

    public PatternInfo(String name, String pathToFile, Redactor owner) {
        this.name = name;
        this.pathToFile = pathToFile;
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Redactor getOwner() {
        return owner;
    }

    public void setOwner(Redactor owner) {
        this.owner = owner;
    }
}
