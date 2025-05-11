package com.example.cardataproject.service.carService;

import com.example.cardataproject.dto.carDTO.CarResponse;
import com.example.cardataproject.dto.producerDTO.ProducerResponse;
import com.example.cardataproject.entity.Car;
import com.example.cardataproject.repository.CarRepository;
import com.example.cardataproject.service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UpdateCarService {

    private final CarRepository carRepository;

    public CarResponse updateCar(Integer carId, Integer mileage, String color) {

        Car carForUpdate = carRepository.updateCar(carId, mileage, color)
                .orElseThrow(() -> new NotFoundException("Car with id " + carId + " not found"));

        return new CarResponse(
                carForUpdate.getCarId(),
                carForUpdate.getVin(),
                carForUpdate.getModel(),
                carForUpdate.getColor(),
                carForUpdate.getYearOfProduction(),
                carForUpdate.getEngine(),
                carForUpdate.getMileage(),
                new ProducerResponse(
                        carForUpdate.getProducer().getProducerId(),
                        carForUpdate.getProducer().getName(),
                        carForUpdate.getProducer().getPhoneNumber(),
                        carForUpdate.getProducer().getEmail()
                ));

    }
}
