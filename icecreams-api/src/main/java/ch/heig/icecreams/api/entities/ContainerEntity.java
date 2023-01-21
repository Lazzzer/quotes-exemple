package ch.heig.icecreams.api.entities;


import jakarta.persistence.*;

@Entity(name = "Container")
@Table(name = "containers")
public class ContainerEntity {
    @TableGenerator(name = "genContainers",
            table = "idContainers",
            pkColumnName = "name",
            valueColumnName = "val",
            initialValue = 3,
            allocationSize = 100)
    @Id // @GeneratedValue
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "genContainers")
    private int id;
    private String name;

    public ContainerEntity() {}

    public ContainerEntity(int id, String name) {
        this.id = id;
        this.name = name;
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
}

