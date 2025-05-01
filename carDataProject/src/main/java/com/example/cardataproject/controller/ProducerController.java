package com.example.cardataproject.controller;


import com.example.cardataproject.dto.producerDTO.ProducerRequest;
import com.example.cardataproject.dto.producerDTO.ProducerResponse;
import com.example.cardataproject.service.producerService.AddProducerService;
import com.example.cardataproject.service.producerService.DeleteProducerService;
import com.example.cardataproject.service.producerService.FindProducerService;
import com.example.cardataproject.service.producerService.UpdateProducerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/producers")
@AllArgsConstructor
public class ProducerController {

    private AddProducerService addProducerService;
    private DeleteProducerService deleteProducerService;
    private FindProducerService findProducerService;
    private UpdateProducerService updateProducerService;


    @PostMapping
    public ProducerResponse createNewProducer(@Valid @RequestBody ProducerRequest producerRequest) {
        return addProducerService.createProducer(producerRequest);
    }

    @GetMapping("/name") // полный путь в строке запроса должен быть /api/producers/name
    public ProducerResponse findByName(@RequestParam String producerName) {
        return findProducerService.findByName(producerName);
    }

    @GetMapping("/all") // полный путь в строке запроса должен быть /api/producers/all
    public List<ProducerResponse> findAll() {
        return findProducerService.findAll();
    }

    @GetMapping("{id}")
    public ProducerResponse findById(@PathVariable Integer id) {
        return findProducerService.findById(id);
    }

    @PutMapping("/{id}")// localhost:8080/api/producers/5?newEmail=audi@example.com&newPhoneNumber=123456789
    public ProducerResponse updateProducer(@PathVariable Integer id,
                                                     @RequestParam String newEmail,
                                                     @RequestParam String newPhoneNumber) {
       return updateProducerService.updateProducer(id, newEmail, newPhoneNumber);
    }

    @DeleteMapping("{id}")
    public ProducerResponse deleteProducerById(@PathVariable Integer id) {
        return deleteProducerService.deleteProducer(id);
    }

}
