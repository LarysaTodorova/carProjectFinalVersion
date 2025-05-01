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
public class DeleteProducerService {

    private final ProducerRepository repository;

    public ProducerResponse deleteProducer(Integer id) {
        Optional<Producer> deletedProducerOptional = repository.deleteProducerById(id);

        if (deletedProducerOptional.isEmpty()) {
            throw new NotFoundException("producer with id " + id + " not found");
        }

        Producer producerForDelete = deletedProducerOptional.get();
        return new ProducerResponse(
                producerForDelete.getProducerId(),
                producerForDelete.getName(),
                producerForDelete.getPhoneNumber(),
                producerForDelete.getEmail()
        );
    }
}
