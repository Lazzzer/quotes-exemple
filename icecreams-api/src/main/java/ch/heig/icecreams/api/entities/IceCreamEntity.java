package ch.heig.icecreams.api.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name = "IceCream")
@Table(name = "ice_creams")
@Getter
@Setter
@NoArgsConstructor
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
}
