package id.muhtadien.apijamaah.controllers;

import id.muhtadien.apijamaah.models.ExcelHelper;
import id.muhtadien.apijamaah.models.services.JamaahService;
import id.muhtadien.apijamaah.responses.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Controller
//@CrossOrigin("http://localhost:8080")
public class FilesController {

    @Autowired
    JamaahService jamaahService;

    @PostMapping(value = "upload")
    public ResponseEntity<CommonResponse> uploadFile(@RequestParam("file") MultipartFile file) {
        String message;
        // Upload and import Excel
        if (ExcelHelper.isExcelFormat(file)) {
            //try {
            try {
                jamaahService.importExcel(file);
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(new CommonResponse(message, 200, null));
            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntity
                        .status(HttpStatus.EXPECTATION_FAILED)
                        .body(new CommonResponse(e.getMessage(), 500, null));
            }
        }

        message = "Please upload an excel file!";
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new CommonResponse(message, 400, null));

    }


}
