package ch.heig.icecreams.api.repositories;

import ch.heig.icecreams.api.entities.ContainerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContainerRepositoy extends JpaRepository<ContainerEntity, Integer> {
}