package id.muhtadien.apijamaah.controllers;

import id.muhtadien.apijamaah.models.entities.JamaahEntity;
import id.muhtadien.apijamaah.models.repositories.JamaahRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "jamaah")
public class JamaahController {

    @Autowired
    JamaahRepository jamaahRepository;

    @GetMapping("")
    public List<JamaahEntity> index() {
        return jamaahRepository.findAll();
    }

    @PostMapping("add")
    public JamaahEntity add(@RequestParam String nama,
                            @RequestParam String alamat,
                            @RequestParam String skill,
                            @RequestParam String status){
        JamaahEntity jamaahEntity = new JamaahEntity();
        jamaahEntity.setAlamat(alamat);
        jamaahEntity.setNama(nama);
        jamaahEntity.setSkill(skill);
        jamaahEntity.setStatus(status);
        jamaahRepository.save(jamaahEntity);
        return jamaahEntity;
    }

    @PostMapping("add")
    public JamaahEntity add(@RequestBody JamaahEntity jamaahEntity){
        jamaahRepository.save(jamaahEntity);
        return jamaahEntity;
    }
}
