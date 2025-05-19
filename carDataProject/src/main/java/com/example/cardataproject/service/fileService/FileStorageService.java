package com.example.cardataproject.service.fileService;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService {

    private final Path FILESTORAGELOCATION = Paths.get("src/main/resources/static/upload");

//    public FileStorageService() {
//        try {
//            Files.createDirectories(FILESTORAGELOCATION); // создаёт папку при запуске
//        } catch (IOException e) {
//            throw new RuntimeException("Could not create upload folder", e);
//        }
//    }

    public void storeFile(MultipartFile file) {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        // убирает (очищает) из имени файла "ошибочные" символы (например: ../document.pdf")

        try {

            Path targetFile = FILESTORAGELOCATION.resolve(fileName);
            Files.copy(file.getInputStream(), targetFile, StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {
            throw new RuntimeException("file saving error " + fileName);
        }
    }
}
