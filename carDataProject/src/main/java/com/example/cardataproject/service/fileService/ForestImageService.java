package com.example.cardataproject.service.fileService;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ForestImageService {

    private final LoadFileFromDirectory lfd;

    public Resource loadForestImage(String filename){
        return lfd.loadFileFromDirectory("src/main/resources/static/files/forest_img", filename);
    }
}
