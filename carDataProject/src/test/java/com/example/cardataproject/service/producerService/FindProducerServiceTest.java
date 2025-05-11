package com.example.cardataproject.service.producerService;

import com.example.cardataproject.dto.producerDTO.ProducerResponse;
import com.example.cardataproject.entity.Producer;
import com.example.cardataproject.repository.ProducerRepository;
import com.example.cardataproject.service.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.List;
import java.util.Optional;

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

    @Test
    void findByIdSuccess() {

        Producer producer = new Producer(1, "BMW", "123456", "bmw@mail.com", "111");

        when(repository.findById(1)).thenReturn(Optional.of(producer));

        ProducerResponse response = findProducerService.findById(1);

        assertEquals(1, response.getProducerId());
    }

    @Test
    void findByIdNotFound() {

        when(repository.findById(1)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> findProducerService.findById(1));

        assertEquals("producer with id 1 not found", exception.getMessage());
    }

    @Test
    void findByNameSuccess() {

        Producer producer = new Producer(1, "BMW", "123456", "bmw@mail.com", "111");

        when(repository.findByName("BMW")).thenReturn(Optional.of(producer));

        ProducerResponse response = findProducerService.findByName("BMW");

        assertEquals("BMW", response.getName());
    }

    @Test
    void findProducerByNameNotFound() {

        when(repository.findByName("BMW")).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> findProducerService.findByName("BMW"));

        assertEquals("producer with name BMW not found", exception.getMessage());

    }

    @Test
    void getEntityByNameSuccess() {

        Producer producer = new Producer(1, "BMW", "123456", "bmw@mail.com", "111");

        when(repository.findByName("BMW")).thenReturn(Optional.of(producer));

        Producer actualResult = findProducerService.getEntityByName("BMW");

        assertEquals("BMW", actualResult.getName());

    }

    @Test
    void findEntityByNameNotFound() {

        when(repository.findByName("Audi")).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> findProducerService.getEntityByName("Audi"));

        assertEquals("Producer with name Audi not found", exception.getMessage());
    }

}