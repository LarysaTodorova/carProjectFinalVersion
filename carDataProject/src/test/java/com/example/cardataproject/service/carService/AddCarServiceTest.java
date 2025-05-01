package com.example.cardataproject.service.carService;

import com.example.cardataproject.dto.producerDTO.ProducerRequest;
import com.example.cardataproject.repository.CarRepository;
import com.example.cardataproject.service.producerService.FindProducerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

class AddCarServiceTest {

    private CarRepository carRepository;
    private FindProducerService findProducerService;
    private AddCarService addCarService;

    @BeforeEach
    void setUp() {
        carRepository = mock(CarRepository.class);
        findProducerService = mock(FindProducerService.class);
        addCarService = new AddCarService(carRepository, findProducerService);
    }

        @Test
        void createCarSuccess () {
            ProducerRequest producerRequest = new ProducerRequest();

        }

    }

