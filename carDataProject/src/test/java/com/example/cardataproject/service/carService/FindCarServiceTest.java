package com.example.cardataproject.service.carService;

import com.example.cardataproject.dto.carDTO.CarResponse;
import com.example.cardataproject.entity.Car;
import com.example.cardataproject.entity.Producer;
import com.example.cardataproject.repository.CarRepository;
import com.example.cardataproject.service.exception.NotFoundException;
import com.example.cardataproject.service.producerService.FindProducerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FindCarServiceTest {

    private CarRepository repository;
    private FindProducerService findProducerService;
    private FindCarService findCarService;

    @BeforeEach
    public void init() {
        repository = mock(CarRepository.class);
        findProducerService = mock(FindProducerService.class);
        findCarService = new FindCarService(repository, findProducerService);
    }

    @Test
    void findAllSuccessful() {

        List<Car> cars = Arrays.asList(
                new Car(1, 123, "A-6", "Red", 2020, "Diesel", 15000, new Producer("Audi", "99553377", "email@producer.com", "123-456")),
                new Car(2, 12346, "X-5", "Black", 2024, "Benzine", 1000, new Producer("BMW", "123999", "bmw@mail.com", "999777"))
        );

        when(repository.findAll()).thenReturn(cars);

        List<CarResponse> actualResult = findCarService.findAll();

        assertEquals(2, actualResult.size());
    }

    @Test
    void findAllCarsNotFound() {

        when(repository.findAll()).thenReturn(List.of());

        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> findCarService.findAll());

        assertEquals("We have no any cars", exception.getMessage());
    }

    @Test
    void findByIdSuccess() {

        Car car = new Car(1, 123, "A-6", "Red", 2020, "Diesel", 15000, new Producer("Audi", "99553377", "email@producer.com", "123-456"));

        when(repository.findById(1)).thenReturn(Optional.of(car));

        CarResponse actualResult = findCarService.findById(1);

        assertEquals(1, actualResult.getCarId());

    }

    @Test
    void findByIdNotFound() {

        when(repository.findById(1)).thenReturn(Optional.empty());

        NotFoundException actualException = assertThrows(NotFoundException.class,
                () -> findCarService.findById(1));

        assertEquals("Car with id 1 not found", actualException.getMessage());
    }
}