package id.muhtadien.apijamaah;

import id.muhtadien.apijamaah.models.services.FilesStorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class ApijamaahApplication implements CommandLineRunner {

    @Resource
    FilesStorageService storageService;

    public static void main(String[] args) {
        SpringApplication.run(ApijamaahApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        storageService.deleteAll();
        storageService.init();
    }
}
