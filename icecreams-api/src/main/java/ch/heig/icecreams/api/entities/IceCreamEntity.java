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
    private int originId;

    public IceCreamEntity() {
    }

    public IceCreamEntity(int id, String name, float price, int originId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.originId = originId;
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

    public void setOriginId(int originId) {
        this.originId = originId;
    }

    public int getOriginId() {
        return originId;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
