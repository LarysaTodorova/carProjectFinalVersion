package com.example.cardataproject.service.producerService;

import com.example.cardataproject.dto.producerDTO.ProducerResponse;
import com.example.cardataproject.entity.Producer;
import com.example.cardataproject.repository.ProducerRepository;
import com.example.cardataproject.service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FindProducerService {

    private final ProducerRepository repository;

    public List<ProducerResponse> findAll() {
        List<Producer> allProducers = repository.findAll();

        if (allProducers.isEmpty()) {
            throw new NotFoundException("We have no any producers");
        }

        return allProducers.stream()
                .map(currentProducer ->
                        new ProducerResponse(
                                currentProducer.getProducerId(),
                                currentProducer.getName(),
                                currentProducer.getPhoneNumber(),
                                currentProducer.getEmail()
                        ))
                .toList();
    }

    public ProducerResponse findById(Integer id) {
        Optional<Producer> foundedProducerOptional = repository.findById(id);

        if (foundedProducerOptional.isEmpty()) {
            throw new NotFoundException("producer with id " + id + " not found");
        }

        Producer foundedProducer = foundedProducerOptional.get();

        return new ProducerResponse(
                foundedProducer.getProducerId(),
                foundedProducer.getName(),
                foundedProducer.getPhoneNumber(),
                foundedProducer.getEmail()
        );

    }

    public ProducerResponse findByName(String name) {
        Optional<Producer> foundedProducersOptional = repository.findByName(name);

        if (foundedProducersOptional.isEmpty()) {
            throw new NotFoundException("producer with name " + name + " not found");
        }

        Producer foundedProducer = foundedProducersOptional.get();

        return new ProducerResponse(
                foundedProducer.getProducerId(),
                foundedProducer.getName(),
                foundedProducer.getPhoneNumber(),
                foundedProducer.getEmail()
        );
    }


    public Producer getEntityByName(String name) {
        return repository.findByName(name)
                .orElseThrow(() -> new NotFoundException("Producer with name " + name + " not found"));
    }
}
