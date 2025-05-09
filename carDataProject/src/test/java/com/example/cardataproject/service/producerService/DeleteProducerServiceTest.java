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

class DeleteProducerServiceTest {

    private ProducerRepository producerRepository;
    private DeleteProducerService deleteProducerService;

    @BeforeEach
    void setUp() {
        producerRepository = mock(ProducerRepository.class);
        deleteProducerService = new DeleteProducerService(producerRepository);
    }

    @Test
    void deleteProducerSuccess() {

        Producer producer = new Producer(
                1,
                "Toyota",
                "123456",
                "toyota@company.com",
                "password"
        );

        when(producerRepository.deleteProducerById(1)).thenReturn(Optional.of(producer));

        ProducerResponse response = deleteProducerService.deleteProducer(1);

        assertEquals(1, response.getProducerId());
        assertEquals("Toyota", response.getName());
        assertEquals("123456", response.getPhoneNumber());
        assertEquals("toyota@company.com", response.getEmail());

        verify(producerRepository).deleteProducerById(1);

    }

    @Test
    void deleteProducerNotFound() {
        when(producerRepository.deleteProducerById(2)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> deleteProducerService.deleteProducer(2)
        );

        assertTrue(exception.getMessage().contains("producer with id 2 not found"));
        verify(producerRepository).deleteProducerById(2);
    }

}