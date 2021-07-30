package id.muhtadien.apijamaah.controllers;

import id.muhtadien.apijamaah.models.entities.JamaahEntity;
import id.muhtadien.apijamaah.models.services.JamaahService;
import id.muhtadien.apijamaah.responses.CommonResponse;
import id.muhtadien.apijamaah.responses.CommonResponseGenerator;
import id.muhtadien.apijamaah.utils.Prop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public CommonResponse index() {
        List<JamaahEntity> jamaahEntities = jamaahService.getAll();
        try{
            return commonResponseGenerator.successResponse(jamaahEntities, Prop.SUCCEEDED);
        }catch (Exception e){
            return commonResponseGenerator.failedResponse(e.getMessage(), 500);
        }
    }

    @GetMapping(value = "search")
    public CommonResponse search(@RequestParam String nama) {
        List<JamaahEntity> jamaahEntities = jamaahService.searchByName(nama);
        try{
            if (jamaahEntities.size() == 0){
                return notFound();
            } else
                return commonResponseGenerator.successResponse(jamaahEntities, Prop.SUCCEEDED);
        }catch (Exception e){
            return commonResponseGenerator.failedResponse(e.getMessage(), 500);
        }
    }

    @PostMapping(value = "add")
    public CommonResponse add(@RequestParam String nama,
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

    @PostMapping(value = "update")
    public CommonResponse update(@RequestParam int id,
                                               @RequestParam String nama,
                                            @RequestParam String alamat,
                                            @RequestParam String skill,
                                            @RequestParam String status){
        JamaahEntity jamaah = jamaahService.update(id, nama, alamat, skill, status);
        try{
            return commonResponseGenerator.successResponse(jamaah, Prop.SUCCEEDED);
        }catch (Exception e){
            return commonResponseGenerator.failedResponse(e.getMessage(), 500);
        }
    }
    //@GetMapping("/methodlevel")
    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Resource was not found on the server")
    public CommonResponse notFound() {
        //return ResponseEntity.status(HttpStatus.NOT_FOUND);
        return commonResponseGenerator.emptyResponse("Not found");
    }

}
