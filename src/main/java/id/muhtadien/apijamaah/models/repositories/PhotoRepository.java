package id.muhtadien.apijamaah.models.repositories;

import id.muhtadien.apijamaah.models.entities.PhotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<PhotoEntity, Long> {
}
