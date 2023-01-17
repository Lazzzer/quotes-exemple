package ch.heig.icecreams.api.entities;


import jakarta.persistence.*;

@Entity(name = "Perfume")
@Table(name = "perfumes")
public class PerfumeEntity {
    @TableGenerator(name = "genPerfumes",
            table = "idPerfumes",
            pkColumnName = "name",
            valueColumnName = "val",
            initialValue = 3,
            allocationSize = 100)
    @Id // @GeneratedValue
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "genPerfumes")
    private int id;

    private String name;

    public PerfumeEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public PerfumeEntity() {}

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
