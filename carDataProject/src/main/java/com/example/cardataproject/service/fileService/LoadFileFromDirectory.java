package com.example.cardataproject.service.fileService;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class LoadFileFromDirectory {

    public Resource loadFileFromDirectory(String directory, String filename) {

        try {
            Path filepath = Paths.get(directory).resolve(filename).normalize();

            Resource resource = new UrlResource(filepath.toUri());

            if (resource.exists() && resource.isReadable()){
                return resource;
            } else {
                throw new RuntimeException("File not found or cannot be read " + filename);
            }

        } catch (MalformedURLException e){
            throw new RuntimeException("Error loading file: " + filename);
        }
    }

}
