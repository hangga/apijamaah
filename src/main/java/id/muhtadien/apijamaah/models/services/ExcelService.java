package id.muhtadien.apijamaah.models.services;

import id.muhtadien.apijamaah.models.ExcelHelper;
import id.muhtadien.apijamaah.models.entities.JamaahEntity;
import id.muhtadien.apijamaah.models.repositories.JamaahRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ExcelService {
    @Autowired
    JamaahRepository repository;

    public void save(MultipartFile file) throws IOException {
        List<JamaahEntity> jamaahEntities = ExcelHelper.excelToJamaahEntitys(file.getInputStream());
        repository.saveAll(jamaahEntities);
    }

}
