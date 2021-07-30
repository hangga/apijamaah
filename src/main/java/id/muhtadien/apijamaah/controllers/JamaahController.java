package id.muhtadien.apijamaah.controllers;

import id.muhtadien.apijamaah.models.entities.JamaahEntity;
import id.muhtadien.apijamaah.models.repositories.JamaahRepository;
import id.muhtadien.apijamaah.responses.CommonResponse;
import id.muhtadien.apijamaah.responses.CommonResponseGenerator;
import id.muhtadien.apijamaah.utils.Prop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "jamaah")
public class JamaahController {

    @Autowired
    JamaahRepository jamaahRepository;

    @Autowired
    CommonResponseGenerator commonResponseGenerator;

    @GetMapping("")
    public CommonResponse<List<JamaahEntity>> index() {
        return commonResponseGenerator.successResponse(jamaahRepository.findAll(), Prop.SUCCEEDED);
    }

    @GetMapping(value = "search")
    public CommonResponse<List<JamaahEntity>> search(@RequestParam String nama) {
        return commonResponseGenerator.successResponse(jamaahRepository.searchByName(nama), Prop.SUCCEEDED);
    }

    @PostMapping(value = "add")
    public CommonResponse<JamaahEntity> add(@RequestParam String nama,
                            @RequestParam String alamat,
                            @RequestParam String skill,
                            @RequestParam String status) {
        JamaahEntity jamaahEntity = new JamaahEntity();
        jamaahEntity.setAlamat(alamat);
        jamaahEntity.setNama(nama);
        jamaahEntity.setSkill(skill);
        jamaahEntity.setStatus(status);
        jamaahRepository.save(jamaahEntity);
        return commonResponseGenerator.successResponse(jamaahEntity, Prop.SUCCEEDED);
    }

    @PostMapping(value = "addbody")
    public CommonResponse<JamaahEntity> add(@RequestBody JamaahEntity jamaahEntity) {
        jamaahRepository.save(jamaahEntity);
        return commonResponseGenerator.successResponse(jamaahEntity, Prop.SUCCEEDED);
    }
}
