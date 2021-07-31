package id.muhtadien.apijamaah.models.services;

import id.muhtadien.apijamaah.models.entities.JamaahEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


public interface JamaahService {
    JamaahEntity add(@RequestParam String nama,
                     @RequestParam String alamat,
                     @RequestParam String skill,
                     @RequestParam String status);

    JamaahEntity update(@RequestParam int id,
                        @RequestParam String nama,
                        @RequestParam String alamat,
                        @RequestParam String skill,
                        @RequestParam String status);

    List<JamaahEntity> getAll();

    List<JamaahEntity> searchByName(@RequestParam String nama);

    void importExcel(MultipartFile file) throws IOException;
}
