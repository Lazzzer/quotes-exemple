package ch.heig.icecreams.api.repositories;

import ch.heig.icecreams.api.entities.IceCreamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IceCreamRepository extends JpaRepository<IceCreamEntity, Integer> {
    default List<IceCreamEntity> findByPrice(Float price) {
        return this.findAll()
                .stream()
                .filter(iceCream -> iceCream.getPrice() == price)
                .toList();
    }
}