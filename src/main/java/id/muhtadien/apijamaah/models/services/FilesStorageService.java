package id.muhtadien.apijamaah.models.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface FilesStorageService {
    void init();

    String save(MultipartFile file, String fileName);

    Resource load(String filename);

    void deleteAll();

    Stream<Path> loadAll();
}
