package ch.heig.icecreams.api.entities;

import jakarta.persistence.*;

@Entity(name = "Origin")
@Table(name = "origins")
public class OriginEntity {
    @TableGenerator(name = "genOrigins",
            table = "idOrigins",
            pkColumnName = "name",
            valueColumnName = "val",
            initialValue = 3,
            allocationSize = 100)
    @Id // @GeneratedValue
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "genOrigins")
    private int id;
    private String name;

    public OriginEntity() {}

    public OriginEntity(int id, String name) {
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


