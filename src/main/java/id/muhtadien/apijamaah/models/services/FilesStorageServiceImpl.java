package id.muhtadien.apijamaah.models.services;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
public class FilesStorageServiceImpl implements FilesStorageService {
    private final Path root = Paths.get("uploads");

    @Override
    public void init() {
        try {
            Files.createDirectory(root);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    @Override
    public String save(MultipartFile file, String fileName) {
        if (!isImage(file)) return "Invalid Image!";
        try {
            String prefix = String.valueOf(System.currentTimeMillis());

            String newFileName = (prefix+"_"+fileName+"_"+file.getOriginalFilename())
                    .replace(" ","")
                    .replace("-","")
                    .replace("—", "");

            Path destination = this.root.resolve(newFileName);

            Files.copy(file.getInputStream(), destination);
            return newFileName;
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }

    @Override
    public Resource load(String filename) {
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(root.toFile());
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Could not load the files!");
        }
    }

    boolean isImage(MultipartFile file){
        return file.getOriginalFilename().contains(".png") ||
                file.getOriginalFilename().contains(".PNG") ||
                file.getOriginalFilename().contains(".jpg") ||
                file.getOriginalFilename().contains(".JPG") ||
                file.getOriginalFilename().contains(".jpeg") ||
                file.getOriginalFilename().contains(".JPEG");
    }
}
