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
import static org.mockito.Mockito.*;

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
        verify(repository).findAll();
    }

    @Test
    void findAllCarsNotFound() {

        when(repository.findAll()).thenReturn(List.of());

        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> findCarService.findAll());

        assertEquals("We have no any cars", exception.getMessage());
        verify(repository).findAll();
    }

    @Test
    void findByIdSuccess() {

        Car car = new Car(1, 123, "A-6", "Red", 2020, "Diesel", 15000, new Producer("Audi", "99553377", "email@producer.com", "123-456"));

        when(repository.findById(1)).thenReturn(Optional.of(car));

        CarResponse actualResult = findCarService.findById(1);

        assertEquals(1, actualResult.getCarId());
        verify(repository).findById(1);

    }

    @Test
    void findByIdNotFound() {

        when(repository.findById(1)).thenReturn(Optional.empty());

        NotFoundException actualException = assertThrows(NotFoundException.class,
                () -> findCarService.findById(1));

        assertEquals("Car with id 1 not found", actualException.getMessage());
        verify(repository).findById(1);
    }

    @Test
    void findByVinSuccess() {

        Car car = new Car(1, 123, "A-6", "Red", 2020, "Diesel", 15000, new Producer("Audi", "99553377", "email@producer.com", "123-456"));

        when(repository.findByVin(123)).thenReturn(Optional.of(car));

        CarResponse actualResult = findCarService.findByVin(123);

        assertEquals(123, actualResult.getVin());
    }

    @Test
    void findByVinNotFound() {

        when(repository.findByVin(123)).thenReturn(Optional.empty());

        NotFoundException actualException = assertThrows(NotFoundException.class,
                () -> findCarService.findByVin(123));

        assertEquals("Car with vin 123 not found", actualException.getMessage());
    }

    @Test
    void findByProducerNameSuccess() {

        Producer producer = new Producer(1, "Audi", "99553377", "email@producer.com", "123-456");
        when(findProducerService.getEntityByName("Audi")).thenReturn(producer);

        Car car = new Car(1, 123, "A-6", "Red", 2020, "Diesel", 15000, producer);
        when(repository.findByProducer(producer)).thenReturn(List.of(car));

        List<CarResponse> actualResult = findCarService.findByProducerName("Audi");

        assertEquals(1, actualResult.size());
        assertEquals("Audi", actualResult.get(0).getProducerResponse().getName());

    }

    @Test
    void findByProducerNameNotFound() {

        when(findProducerService.getEntityByName("Audi")).thenThrow(new NotFoundException("Producer with name Audi not found"));

        NotFoundException actualException = assertThrows(NotFoundException.class,
                () -> findCarService.findByProducerName("Audi"));

        assertEquals("Producer with name Audi not found", actualException.getMessage());

    }

    @Test
    void findCarByModelSuccess() {

        List<Car> cars = Arrays.asList(
                new Car(1, 123, "A-6", "Red", 2020, "V-6", 15000, new Producer("Audi", "99553377", "email@producer.com", "123-456")),
                new Car(2, 999, "A-6", "white", 2025, "V-8", 1, new Producer(1, "Audi", "555", "audi@mail.com", "111"))
        );

        when(repository.findByModel("A-6")).thenReturn(cars);

        List<CarResponse> actualResult = findCarService.findCarByModel("A-6");

        assertEquals(2, actualResult.size());
        assertEquals("A-6", actualResult.get(0).getModel());
        assertEquals("A-6", actualResult.get(1).getModel());

    }

    @Test
    void findCarByModelNotFound() {

        when(repository.findByModel("A-6")).thenReturn(List.of());

        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> findCarService.findCarByModel("A-6"));

        assertEquals("Car with model A-6 not found", exception.getMessage());
    }

    @Test
    void findCarByYearSuccess() {

        List<Car> cars = Arrays.asList(
                new Car(1, 123, "A-6", "Red", 2020, "V-6", 15000, new Producer("Audi", "99553377", "email@producer.com", "123-456")),
                new Car(2, 999, "A-6", "white", 2025, "V-8", 1, new Producer(1, "Audi", "555", "audi@mail.com", "111"))
        );

        when(repository.findByYearOfProduction(2025)).thenReturn(cars);

        List<CarResponse> actualResult = findCarService.findCarByYear(2025);

        assertEquals(2025, actualResult.get(1).getYearOfProduction());
    }

    @Test
    void findCarByYearNotFound() {

        when(repository.findByYearOfProduction(2025)).thenReturn(List.of());

        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> findCarService.findCarByYear(2025));

        assertEquals("Car with year 2025 not found", exception.getMessage());
    }
}