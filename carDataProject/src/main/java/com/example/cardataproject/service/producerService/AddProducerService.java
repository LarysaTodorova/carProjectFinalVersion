package com.example.cardataproject.service.producerService;

import com.example.cardataproject.dto.producerDTO.ProducerRequest;
import com.example.cardataproject.dto.producerDTO.ProducerResponse;
import com.example.cardataproject.entity.Producer;
import com.example.cardataproject.repository.ProducerRepository;
import com.example.cardataproject.service.exception.AlreadyExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddProducerService {

    private final ProducerRepository repository;

    public ProducerResponse createProducer(ProducerRequest request) {

        if (repository.findByName(request.getName()).isPresent()) {
            throw new AlreadyExistException("Producer with name" + request.getName() + " already exist");
        }

        Producer producerBeforeCreating = new Producer(
                request.getName(),
                request.getPhoneNumber(),
                request.getEmail(),
                request.getPassword()
        );

        Producer savedProducer = repository.save(producerBeforeCreating);

        return new ProducerResponse(
                savedProducer.getProducerId(),
                savedProducer.getName(),
                savedProducer.getPhoneNumber(),
                savedProducer.getEmail()
        );
    }
}
