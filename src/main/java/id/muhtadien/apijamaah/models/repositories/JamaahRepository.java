package id.muhtadien.apijamaah.models.repositories;

import id.muhtadien.apijamaah.models.entities.JamaahEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JamaahRepository extends JpaRepository<JamaahEntity, Long> {
    @Query(value = "select * from jamaah where nama like %:nama%", nativeQuery=true)
    List<JamaahEntity> searchByName(@Param("nama") String nama);
}
