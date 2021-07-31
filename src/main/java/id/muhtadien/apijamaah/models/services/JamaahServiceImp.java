package id.muhtadien.apijamaah.models.services;

import id.muhtadien.apijamaah.models.ExcelHelper;
import id.muhtadien.apijamaah.models.entities.JamaahEntity;
import id.muhtadien.apijamaah.models.repositories.JamaahRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class JamaahServiceImp implements JamaahService{

    @Autowired
    JamaahRepository jamaahRepository;

    @Override
    public JamaahEntity add(String nama, String alamat, String skill, String status) {
        JamaahEntity jamaahEntity = new JamaahEntity();
        jamaahEntity.setAlamat(alamat);
        jamaahEntity.setNama(nama);
        jamaahEntity.setSkill(skill);
        jamaahEntity.setStatus(status);
        jamaahRepository.save(jamaahEntity);
        return jamaahEntity;
    }

    @Override
    public JamaahEntity update(int id, String nama, String alamat, String skill, String status) {
        JamaahEntity jamaah = new JamaahEntity();
        jamaah.setId(id);
        jamaah.setAlamat(alamat);
        jamaah.setNama(nama);
        jamaah.setSkill(skill);
        jamaah.setStatus(status);
        jamaahRepository.save(jamaah);
        return jamaah;
    }

    @Override
    public List<JamaahEntity> getAll() {
        return jamaahRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));
    }

    @Override
    public List<JamaahEntity> searchByName(String nama) {
        return jamaahRepository.searchByName(nama);
    }

    @Override
    public void importExcel(MultipartFile file) throws IOException {
        List<JamaahEntity> jamaahEntities = ExcelHelper.excelToJamaahEntitys(file.getInputStream());
        jamaahRepository.saveAll(jamaahEntities);
    }
}
