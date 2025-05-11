package com.example.cardataproject.service.carService;

import com.example.cardataproject.dto.carDTO.CarResponse;
import com.example.cardataproject.entity.Car;
import com.example.cardataproject.entity.Producer;
import com.example.cardataproject.repository.CarRepository;
import com.example.cardataproject.service.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DeleteCarServiceTest {

    private CarRepository repository;
    private DeleteCarService deleteCarService;

    @BeforeEach
    void init() {
        repository = mock(CarRepository.class);
        deleteCarService = new DeleteCarService(repository);
    }

    @Test
    void deleteCarSuccess() {

        Car car = new Car(0,
                123,
                "A-6",
                "Red",
                2020,
                "Diesel",
                15000,
                new Producer("Audi", "99553377", "email@producer.com", "123-456"));

        when(repository.findById(0)).thenReturn(Optional.of(car));
        when(repository.deleteCarByCarId(0)).thenReturn(Optional.of(car));

        CarResponse actualResult = deleteCarService.deleteCarById(0);

        assertEquals(0, actualResult.getCarId());
        verify(repository).findById(0);
        verify(repository).deleteCarByCarId(0);
    }

    @Test
    void deleteCarNotFound() {

        when(repository.findById(5)).thenReturn(Optional.empty());
        when(repository.deleteCarByCarId(5)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> deleteCarService.deleteCarById(5));

        assertEquals("Car with id 5 not found", exception.getMessage());
        verify(repository).findById(5);
    }

}