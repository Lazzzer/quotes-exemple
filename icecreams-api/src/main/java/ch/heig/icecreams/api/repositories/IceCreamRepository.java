package ch.heig.icecreams.api.repositories;

import ch.heig.icecreams.api.entities.IceCreamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IceCreamRepository extends JpaRepository<IceCreamEntity, Integer> {
    IceCreamEntity findById(int id);
    List<IceCreamEntity> findByPrice(String pattern);

    List<IceCreamEntity> findByOriginId(int id);
}