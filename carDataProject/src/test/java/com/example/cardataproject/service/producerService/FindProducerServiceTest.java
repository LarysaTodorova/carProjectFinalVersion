package com.example.cardataproject.service.producerService;

import com.example.cardataproject.dto.producerDTO.ProducerResponse;
import com.example.cardataproject.entity.Producer;
import com.example.cardataproject.repository.ProducerRepository;
import com.example.cardataproject.service.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FindProducerServiceTest {

    private ProducerRepository repository;
    private FindProducerService findProducerService;

    @BeforeEach
    public void init() {
        repository = mock(ProducerRepository.class);
        findProducerService = new FindProducerService(repository);

    }

    @Test
    public void findAllSuccess() {

        Producer producer1 = new Producer(1, "BMW", "123456", "bmw@mail.com", "111");
        Producer producer2 = new Producer(2, "Toyota", "123456789", "toyota@company.com", "password");

        when(repository.findAll()).thenReturn(List.of(producer1, producer2));

        List<ProducerResponse> actualResult = findProducerService.findAll();


        List<ProducerResponse> expectedResult = List.of(
                new ProducerResponse(1, "BMW", "123456", "bmw@mail.com"),
                new ProducerResponse(2, "Toyota", "123456789", "toyota@company.com")
        );

        assertEquals(expectedResult, actualResult);

    }

    @Test
    public void findAllNotFound() {

        when(repository.findAll()).thenReturn(List.of());
        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> findProducerService.findAll());

        assertEquals("We have no any producers", exception.getMessage());

    }

}