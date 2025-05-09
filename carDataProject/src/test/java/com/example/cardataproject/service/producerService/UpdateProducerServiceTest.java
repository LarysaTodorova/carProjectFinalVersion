package com.example.cardataproject.service.producerService;

import com.example.cardataproject.dto.producerDTO.ProducerResponse;
import com.example.cardataproject.entity.Producer;
import com.example.cardataproject.repository.ProducerRepository;
import com.example.cardataproject.service.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UpdateProducerServiceTest {

    private ProducerRepository repository;
    private UpdateProducerService updateService;

    @BeforeEach
    void setUp() {
        repository = mock(ProducerRepository.class);
        updateService = new UpdateProducerService(repository);
    }

    @Test
    void updateProducerSuccess() {

        Producer producer = new Producer(
                1,
                "Audi",
                "123456",
                "audi@company.com",
                "password"
        );

        when(repository.updateProducer(1, "audi@company.com", "123456"))
                .thenReturn(Optional.of(producer));

        ProducerResponse response = updateService.updateProducer(1, "audi@company.com", "123456");

        assertEquals(1, response.getProducerId());
        assertEquals("Audi", response.getName());
        assertEquals("123456", response.getPhoneNumber());
        assertEquals("audi@company.com", response.getEmail());

        verify(repository).updateProducer(1, "audi@company.com", "123456");

    }

    @Test
    void updateProducerNotFound() {

        when(repository.updateProducer(2, "asdf@mail.com", "0000"))
                .thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> updateService.updateProducer(2, "asdf@mail.com", "0000")
        );

        assertTrue(exception.getMessage().contains("Producer with id 2 not found"));
        verify(repository).updateProducer(2, "asdf@mail.com", "0000");
    }




}