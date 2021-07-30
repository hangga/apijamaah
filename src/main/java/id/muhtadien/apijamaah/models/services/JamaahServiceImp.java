package id.muhtadien.apijamaah.models.services;

import id.muhtadien.apijamaah.models.entities.JamaahEntity;
import id.muhtadien.apijamaah.models.repositories.JamaahRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<JamaahEntity> getAll() {
        return jamaahRepository.findAll();
    }

    @Override
    public List<JamaahEntity> searchByName(String nama) {
        return jamaahRepository.searchByName(nama);
    }
}
