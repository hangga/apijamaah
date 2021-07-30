package id.muhtadien.apijamaah.models.repositories;

import id.muhtadien.apijamaah.models.entities.JamaahEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JamaahRepository extends JpaRepository<JamaahEntity, Long> {
    @Query(value = "select * from jamaah where nama like %:nama%", nativeQuery = true)
    List<JamaahEntity> searchByName(@Param("nama") String nama);

    @Query(value = "update jamaah set nama=:nama, alamat=:alamat, skill=:skill, status=:status where id=:id", nativeQuery = true)
    void update(@Param("nama") String nama,
                @Param("alamat") String alamat,
                @Param("skill") String skill,
                @Param("status") String status,
                @Param("id") int id);
}
