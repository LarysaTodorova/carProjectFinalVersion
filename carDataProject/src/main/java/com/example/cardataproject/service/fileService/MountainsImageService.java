package com.example.cardataproject.service.fileService;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MountainsImageService {

    private final LoadFileFromDirectory lfd;

    public Resource loadMountainImage(String filename){
        return lfd.loadFileFromDirectory("src/main/resources/static/files/mountains_img", filename);
    }
}
