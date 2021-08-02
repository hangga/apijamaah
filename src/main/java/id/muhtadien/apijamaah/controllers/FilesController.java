package id.muhtadien.apijamaah.controllers;

import id.muhtadien.apijamaah.models.ExcelHelper;
import id.muhtadien.apijamaah.models.FileInfo;
import id.muhtadien.apijamaah.models.services.FilesStorageService;
import id.muhtadien.apijamaah.models.services.JamaahService;
import id.muhtadien.apijamaah.responses.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@Controller
//@CrossOrigin("http://localhost:8080")
public class FilesController {

    @Autowired
    FilesStorageService storageService;

    @Autowired
    JamaahService jamaahService;

    @PostMapping(value = "upload")
    public ResponseEntity<CommonResponse> uploadExcelFile(@RequestParam("file") MultipartFile file) {
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

    @PostMapping(value = "justupload")
    public ResponseEntity<CommonResponse> uploadFile(@RequestParam("file") MultipartFile file) {
        String message;
        try {
            storageService.save(file, "file baru");
            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new CommonResponse(message, 200, null));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!\n"+ e.getMessage();
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new CommonResponse(message, 417, null));
        }
    }

    @GetMapping(value = "files")
    public ResponseEntity<List<FileInfo>> getListFiles() {
        List<FileInfo> fileInfos = storageService.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder
                    .fromMethodName(FilesController.class, "getFile", path.getFileName().toString()).build().toString();

            return new FileInfo(filename, url);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = storageService.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

}
