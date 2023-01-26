package ch.heig.icecreams.api.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "IceCream")
@Table(name = "ice_creams")
public class IceCreamEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private float price;
    @ManyToOne(optional = false)
    private OriginEntity origin;
    @ManyToMany
    private List<ContainerEntity> containers;

    public IceCreamEntity() {
    }

    public IceCreamEntity(int id, String name, float price, OriginEntity origin) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.origin = origin;
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

    public float getPrice() {
        return price;
    }

    public void setOrigin(OriginEntity origin) {
        this.origin = origin;
    }

    public OriginEntity getOrigin() {
        return origin;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public List<ContainerEntity> getContainers() {
        return containers;
    }

    public void setContainers(List<ContainerEntity> containers) {
        this.containers = containers;
    }
}
