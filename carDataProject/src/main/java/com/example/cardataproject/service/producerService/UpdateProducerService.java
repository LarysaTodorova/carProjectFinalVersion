package com.example.cardataproject.service.producerService;

import com.example.cardataproject.dto.producerDTO.ProducerResponse;
import com.example.cardataproject.entity.Producer;
import com.example.cardataproject.repository.ProducerRepository;
import com.example.cardataproject.service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UpdateProducerService {

    private final ProducerRepository repository;


    public ProducerResponse updateProducer(Integer id, String email, String phoneNumber) {

        if (email == null || email.isBlank() || phoneNumber == null || phoneNumber.isBlank()) {
            throw new IllegalArgumentException("Email and phone number must not be null or blank");
        }

        Optional<Producer> optionalProducer = repository.updateProducer(id, email, phoneNumber);

        if (optionalProducer.isEmpty()) {
            throw new NotFoundException("Producer with id " + id + " not found");
        }

        Producer producerForUpdate = optionalProducer.get();

        return new ProducerResponse(
                producerForUpdate.getProducerId(),
                producerForUpdate.getName(),
                producerForUpdate.getPhoneNumber(),
                producerForUpdate.getEmail()
        );

    }
}
