package ch.heig.icecreams.api.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name = "Container")
@Table(name = "containers")
@Getter
@Setter
@NoArgsConstructor
public class ContainerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @ManyToMany(mappedBy = "containers", fetch = FetchType.LAZY)
    private List<IceCreamEntity> iceCreams;
}

