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

    @GetMapping(value = "getdata")
    public ResponseEntity<CommonResponse> getall(@RequestParam(defaultValue = "0") int pageNo,
                                                 @RequestParam(defaultValue = "10") int pageSize){
        List<JamaahEntity> jamaahEntities = jamaahService.getAll(pageNo, pageSize);
        try {
            if (jamaahEntities.size() == 0) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(commonResponseGenerator.emptyResponse("Not found"));
            } else
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(commonResponseGenerator.successResponse(jamaahEntities, Prop.SUCCEEDED));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(commonResponseGenerator.failedResponse(e.getMessage(),
                            HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }
    }

    @GetMapping("")
    public ResponseEntity<CommonResponse> index() {
        List<JamaahEntity> jamaahEntities = jamaahService.getAll();
        try {
            if (jamaahEntities.size() == 0) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(commonResponseGenerator.emptyResponse("Not found"));
            } else
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(commonResponseGenerator.successResponse(jamaahEntities, Prop.SUCCEEDED));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(commonResponseGenerator.failedResponse(e.getMessage(),
                            HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }
    }

    @GetMapping(value = "search")
    public ResponseEntity<CommonResponse> search(@RequestParam String nama) {
        List<JamaahEntity> jamaahEntities = jamaahService.searchByName(nama);
        try {
            if (jamaahEntities.size() == 0) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(commonResponseGenerator.emptyResponse("Not found"));
            } else
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(commonResponseGenerator.successResponse(jamaahEntities, Prop.SUCCEEDED));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(commonResponseGenerator.failedResponse(e.getMessage(),
                            HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }
    }

    @PostMapping(value = "add")
    public ResponseEntity<CommonResponse> add(@RequestParam String nama,
                              @RequestParam String alamat,
                              @RequestParam String skill,
                              @RequestParam String status) {
        JamaahEntity jamaah = jamaahService.add(nama, alamat, skill, status);
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(commonResponseGenerator.successResponse(jamaah, Prop.SUCCEEDED));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(commonResponseGenerator.failedResponse(e.getMessage(),
                            HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }
    }

    @PostMapping(value = "update")
    public ResponseEntity<CommonResponse> update(@RequestParam int id,
                                 @RequestParam String nama,
                                 @RequestParam String alamat,
                                 @RequestParam String skill,
                                 @RequestParam String status) {
        JamaahEntity jamaah = jamaahService.update(id, nama, alamat, skill, status);
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(commonResponseGenerator.successResponse(jamaah, Prop.SUCCEEDED));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(commonResponseGenerator.failedResponse(e.getMessage(),
                            HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }
    }

}
