package ch.heig.icecreams.api.repositories;

import ch.heig.icecreams.api.entities.ContainerEntity;
import ch.heig.icecreams.api.entities.OriginEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContainerRepositoy extends JpaRepository<ContainerEntity, Integer> {
    OriginEntity findById(int id);
    List<OriginEntity> findByName(String pattern);
}