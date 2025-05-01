package com.example.cardataproject.service.producerService;

import com.example.cardataproject.dto.producerDTO.ProducerRequest;
import com.example.cardataproject.dto.producerDTO.ProducerResponse;
import com.example.cardataproject.entity.Producer;
import com.example.cardataproject.repository.ProducerRepository;
import com.example.cardataproject.service.exception.AlreadyExistException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AddProducerServiceTest {

    private ProducerRepository producerRepository;
    private AddProducerService addProducerService;


    @BeforeEach
    public void init() {
        producerRepository = mock(ProducerRepository.class);
        addProducerService = new AddProducerService(producerRepository);
    }

    @Test
    public void createProducerSuccess() {

        ProducerRequest request = new ProducerRequest(
                "BMW",
                "123456",
                "bmw@company.com",
                "1111111"
        );

        when(producerRepository.findByName("BMW")).thenReturn(Optional.empty());

        Producer savedProducer = new Producer(
                "BMW",
                "123456",
                "bmw@company.com",
                "1111111"
        );

        when(producerRepository.save(any(Producer.class))).thenReturn(savedProducer);

        ProducerResponse response = addProducerService.createProducer(request);

        assertEquals("BMW", response.getName());
        assertEquals("bmw@company.com", response.getEmail());
        verify(producerRepository, times(1)).save(any(Producer.class));

    }

    @Test
    public void createProducerAlreadyExist() {

        ProducerRequest request = new ProducerRequest(
                "BMW",
                "123456",
                "bmw@company.com",
                "1111111"
        );

        Producer existingProducer = new Producer(
                "BMW",
                "123456",
                "bmw@company.com",
                "1111111"
        );

        when(producerRepository.findByName("BMW")).thenReturn(Optional.of(existingProducer));

        AlreadyExistException exception = assertThrows(
                AlreadyExistException.class,
                () -> addProducerService.createProducer(request)
        );

        assertTrue(exception.getMessage().contains("already exist"));
        verify(producerRepository,
                never()).save(any());

    }}
