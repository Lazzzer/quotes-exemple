package ch.heig.icecreams.api.entities;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "Container")
@Table(name = "containers")
public class ContainerEntity {
    @Id
    @GeneratedValue
    private int id;
    private String name;

    /*@ManyToMany(mappedBy = "containers")
    private List<IceCreamEntity> icecreams = new ArrayList<>();*/

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

