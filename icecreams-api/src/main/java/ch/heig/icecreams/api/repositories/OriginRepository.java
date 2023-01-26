package ch.heig.icecreams.api.repositories;

import ch.heig.icecreams.api.entities.OriginEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OriginRepository extends JpaRepository<OriginEntity, Integer> {
}