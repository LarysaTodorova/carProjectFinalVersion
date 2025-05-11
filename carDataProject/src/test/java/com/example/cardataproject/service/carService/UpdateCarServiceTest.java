package com.example.cardataproject.service.carService;

import com.example.cardataproject.dto.carDTO.CarResponse;
import com.example.cardataproject.entity.Car;
import com.example.cardataproject.entity.Producer;
import com.example.cardataproject.repository.CarRepository;
import com.example.cardataproject.service.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class UpdateCarServiceTest {

    private CarRepository repository;
    private UpdateCarService updateCarService;

    @BeforeEach
    void init() {

        repository = mock(CarRepository.class);
        updateCarService = new UpdateCarService(repository);
    }

    @Test
    void updateCarSuccess() {

        Car car = new Car(
                0,
                123,
                "A-6",
                "red",
                2020,
                "Diesel",
                20000,
                new Producer("Audi", "99553377", "email@producer.com", "123-456"));

        when(repository.updateCar(0, 20000, "red")).thenReturn(Optional.of(car));

        // Вызываем метод сервиса
        CarResponse actualResult = updateCarService.updateCar(0, 20000, "red");

        // Проверяем, что результат совпадает с ожидаемым
        assertEquals(0, actualResult.getCarId());
        assertEquals(20000, actualResult.getMileage());
        assertEquals("red", actualResult.getColor());

        // Проверяем, что метод репозитория был вызван с нужными параметрами
        verify(repository).updateCar(0, 20000, "red");
    }

    @Test
    void updateCarNotFound() {

        when(repository.updateCar(0, 2000, "red")).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> updateCarService.updateCar(0, 20000, "red"));

        assertEquals("Car with id 0 not found", exception.getMessage());

        verify(repository).updateCar(0, 20000, "red");
    }

}