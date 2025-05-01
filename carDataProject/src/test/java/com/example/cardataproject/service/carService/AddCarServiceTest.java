package com.example.cardataproject.service.carService;

import com.example.cardataproject.dto.carDTO.CarRequest;
import com.example.cardataproject.dto.carDTO.CarResponse;
import com.example.cardataproject.dto.producerDTO.ProducerRequest;
import com.example.cardataproject.entity.Car;
import com.example.cardataproject.entity.Producer;
import com.example.cardataproject.repository.CarRepository;
import com.example.cardataproject.service.producerService.FindProducerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
    void createCarSuccess() {

        ProducerRequest producerRequest = new ProducerRequest(
                "Tesla",
                "+1-111-111",
                "tesla@example.com",
                "securePass"
        );

        CarRequest carRequest = new CarRequest(
                123456,
                "Model X",
                "Black",
                2023,
                "Electric",
                15000,
                producerRequest
        );

        Producer producer = new Producer(
                "Tesla",
                "+1-111-111",
                "tesla@example.com",
                "securePass"
        );

        Car savedCar = new Car(
                1,
                123456,
                "Model X",
                "Black",
                2023,
                "Electric",
                15000,
                producer
        );

        when(carRepository.findByVin(123456)).thenReturn(Optional.empty());
        when(findProducerService.getEntityByName("Tesla")).thenReturn(producer);
        when(carRepository.save(any(Car.class))).thenReturn(savedCar);

        CarResponse carResponse = addCarService.createCar(carRequest);

        assertEquals(123456, carResponse.getVin());
        assertEquals("Model X", carResponse.getModel());
        assertEquals("Black", carResponse.getColor());
        assertEquals(2023, carResponse.getYearOfProduction());
        assertEquals("Electric", carResponse.getEngine());
        assertEquals(15000, carResponse.getMileage());

        assertNotNull(carResponse.getProducerResponse());
        assertEquals("Tesla", carResponse.getProducerResponse().getName());
        assertEquals("tesla@example.com", carResponse.getProducerResponse().getEmail());

        verify(carRepository).save(any(Car.class));
        verify(findProducerService).getEntityByName("Tesla");

    }

}

