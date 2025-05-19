package com.example.cardataproject.controller;

import com.example.cardataproject.service.fileService.ForestImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/image/forest")
public class ForestImageController {
    private final ForestImageService service;

    @GetMapping("/{filename}")
    public ResponseEntity<Resource> getForestImage(@PathVariable String filename){
        Resource image = service.loadForestImage(filename);

        // определяем типа содержимого для отправки как изображение

        MediaType mediaType = MediaType.IMAGE_JPEG;

        // необходимо создать заголовок для ответа

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaType);

        // надо установить заголовок Content - Disposition чтобы броаузер
        // знал, что делать с содержимым нашего ответа (по умолчанию - сохраняет на диск)

        headers.setContentDispositionFormData("inline", filename);

        // возвращаем ответ с байтами изображения, заголовком и статусом

        return ResponseEntity.ok()
                .headers(headers)
                .body(image);
    }
}
