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
public class DeleteCarService {

    private final CarRepository carRepository;

    public CarResponse deleteCarById(Integer carId) {

        Optional<Car> optionalCar = carRepository.findById(carId);

        if (optionalCar.isEmpty()) {
            throw new NotFoundException("Car with id " + carId + " not found");
        }

        Car foundcar = optionalCar.get();
        carRepository.deleteCarByCarId(carId);

        return new CarResponse(
                foundcar.getCarId(),
                foundcar.getVin(),
                foundcar.getModel(),
                foundcar.getColor(),
                foundcar.getYearOfProduction(),
                foundcar.getEngine(),
                foundcar.getMileage(),
                new ProducerResponse(
                        foundcar.getProducer().getProducerId(),
                        foundcar.getProducer().getName(),
                        foundcar.getProducer().getPhoneNumber(),
                        foundcar.getProducer().getEmail()

                )
        );
    }
}
