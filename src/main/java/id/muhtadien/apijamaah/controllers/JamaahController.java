package id.muhtadien.apijamaah.controllers;

import id.muhtadien.apijamaah.models.entities.JamaahEntity;
import id.muhtadien.apijamaah.models.services.JamaahService;
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
    CommonResponseGenerator commonResponseGenerator;


    @Autowired
    JamaahService jamaahService;


    @GetMapping("")
    public CommonResponse<List<JamaahEntity>> index() {
        List<JamaahEntity> jamaahEntities = jamaahService.getAll();
        try{
            return commonResponseGenerator.successResponse(jamaahEntities, Prop.SUCCEEDED);
        }catch (Exception e){
            return commonResponseGenerator.failedResponse(e.getMessage(), 500);
        }
    }

    @GetMapping(value = "search")
    public CommonResponse<List<JamaahEntity>> search(@RequestParam String nama) {
        List<JamaahEntity> jamaahEntities = jamaahService.searchByName(nama);
        try{
            return commonResponseGenerator.successResponse(jamaahEntities, Prop.SUCCEEDED);
        }catch (Exception e){
            return commonResponseGenerator.failedResponse(e.getMessage(), 500);
        }
    }

    @PostMapping(value = "add")
    public CommonResponse<JamaahEntity> add(@RequestParam String nama,
                            @RequestParam String alamat,
                            @RequestParam String skill,
                            @RequestParam String status) {
        JamaahEntity jamaah = jamaahService.add(nama, alamat, skill, status);
        try{
            return commonResponseGenerator.successResponse(jamaah, Prop.SUCCEEDED);
        }catch (Exception e){
            return commonResponseGenerator.failedResponse(e.getMessage(), 500);
        }
    }

}
