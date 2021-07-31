package id.muhtadien.apijamaah.controllers;

import id.muhtadien.apijamaah.models.entities.JamaahEntity;
import id.muhtadien.apijamaah.models.services.JamaahService;
import id.muhtadien.apijamaah.responses.CommonResponse;
import id.muhtadien.apijamaah.responses.CommonResponseGenerator;
import id.muhtadien.apijamaah.utils.Prop;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.FileOutputStream;
import java.util.List;

@RestController
@RequestMapping(value = "jamaah")
public class JamaahController {

    @Autowired
    CommonResponseGenerator commonResponseGenerator;


    @Autowired
    JamaahService jamaahService;


    @GetMapping("")
    public ResponseEntity<CommonResponse> index() {
        List<JamaahEntity> jamaahEntities = jamaahService.getAll();
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(commonResponseGenerator.successResponse(jamaahEntities, Prop.SUCCEEDED));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(commonResponseGenerator.failedResponse(e.getMessage(), 500));
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
                    .status(HttpStatus.OK)
                    .body(commonResponseGenerator.failedResponse(e.getMessage(), 500));
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
                    .status(HttpStatus.OK)
                    .body(commonResponseGenerator.failedResponse(e.getMessage(), 500));
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
                    .status(HttpStatus.OK)
                    .body(commonResponseGenerator.failedResponse(e.getMessage(), 500));
        }
    }

    //@GetMapping("/methodlevel")
    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Resource was not found on the server")
    public CommonResponse notFound() {
        //return ResponseEntity.status(HttpStatus.NOT_FOUND);
        return commonResponseGenerator.emptyResponse("Not found");
    }

    @RequestMapping(value="/uploadImage2",method = RequestMethod.POST)
    public @ResponseBody String uploadImage2(@RequestParam("imageValue") String imageValue, HttpServletRequest request){
        try
        {
            //This will decode the String which is encoded by using Base64 class
            byte[] imageByte = Base64.decodeBase64(imageValue);

            String directory= request.getRealPath("/")+"images/sample.jpg";

            new FileOutputStream(directory).write(imageByte);
            return "success ";
        }
        catch(Exception e)
        {
            return "error = "+e;
        }

    }
}
