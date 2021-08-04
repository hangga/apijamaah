package id.muhtadien.apijamaah.models.services;

import id.muhtadien.apijamaah.models.entities.JamaahEntity;
import id.muhtadien.apijamaah.models.repositories.JamaahRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class JamaahServiceImp implements JamaahService{

    @Autowired
    JamaahRepository jamaahRepository;

    @Override
    public JamaahEntity add(String nama, String alamat, String skill, String status, String photoUrl) {
        JamaahEntity jamaahEntity = new JamaahEntity();
        jamaahEntity.setAlamat(alamat);
        jamaahEntity.setNama(nama);
        jamaahEntity.setSkill(skill);
        jamaahEntity.setStatus(status);
        jamaahEntity.setPhotoUrl(photoUrl);
        jamaahRepository.save(jamaahEntity);
        return jamaahEntity;
    }

    @Override
    public JamaahEntity update(int id, String nama, String alamat, String skill, String status, String photoUrl) {
        JamaahEntity jamaah = new JamaahEntity();
        jamaah.setId(id);
        jamaah.setAlamat(alamat);
        jamaah.setNama(nama);
        jamaah.setSkill(skill);
        jamaah.setStatus(status);
        jamaah.setPhotoUrl(photoUrl);
        jamaahRepository.save(jamaah);
        return jamaah;
    }

    @Override
    public List<JamaahEntity> getAll() {
        return jamaahRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));
    }

    @Override
    public List<JamaahEntity> getAll(int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("id").descending());
        Page<JamaahEntity> pagedResult = jamaahRepository.findAll(paging);
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public List<JamaahEntity> searchByName(String nama) {
        return jamaahRepository.searchByName(nama);
    }

    @Override
    public void importExcel(MultipartFile file) throws IOException {
        List<JamaahEntity> jamaahEntities = JamaahExcelHelper.excelToJamaahEntitys(file.getInputStream());
        jamaahRepository.saveAll(jamaahEntities);
    }
}
