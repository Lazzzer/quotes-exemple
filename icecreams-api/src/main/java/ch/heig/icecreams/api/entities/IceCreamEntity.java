package ch.heig.icecreams.api.entities;


import jakarta.persistence.*;

@Entity(name = "IceCream")
@Table(name = "ice_creams")
public class IceCreamEntity {
    @TableGenerator(name = "genIceCreams",
            table = "idIceCreams",
            pkColumnName = "name",
            valueColumnName = "val",
            initialValue = 3,
            allocationSize = 100)
    @Id // @GeneratedValue
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "genIceCreams")
    private int id;
    private String name;
    private float price;

    public IceCreamEntity() {}

    public IceCreamEntity(int id, String name, float price) {
        this.id = id;
        this.name = name;
        this.price = price;
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

    public void setPrice(float price) {
        this.price = price;
    }
}
