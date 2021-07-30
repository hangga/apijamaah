package id.muhtadien.apijamaah.models.repositories;

import id.muhtadien.apijamaah.models.entities.JamaahEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JamaahRepository extends JpaRepository<JamaahEntity, Long> {
}
