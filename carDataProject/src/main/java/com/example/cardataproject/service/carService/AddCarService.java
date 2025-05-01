package com.example.cardataproject.service.carService;

import com.example.cardataproject.dto.carDTO.CarRequest;
import com.example.cardataproject.dto.carDTO.CarResponse;
import com.example.cardataproject.dto.producerDTO.ProducerResponse;
import com.example.cardataproject.entity.Car;
import com.example.cardataproject.entity.Producer;
import com.example.cardataproject.repository.CarRepository;
import com.example.cardataproject.service.exception.AlreadyExistException;
import com.example.cardataproject.service.producerService.FindProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AddCarService {

    private final CarRepository carRepository;
    private final FindProducerService producerService;

    public CarResponse createCar(CarRequest request) {

        // Проверка: существует ли уже машина с таким VIN
        Optional<Car> optionalCar = carRepository.findByVin(request.getVin());

        if (optionalCar.isPresent()) {
            throw new AlreadyExistException("Car with vin " + request.getVin() + " already exists");
        }

        // Получаем продюсера
        Producer producer = producerService.getEntityByName(request.getProducerRequest().getName());

        // Создаём и сохраняем машину
        Car carToCreate = new Car(
                null,
                request.getVin(),
                request.getModel(),
                request.getColor(),
                request.getYearOfProduction(),
                request.getEngine(),
                request.getMileage(),
                producer
        );

        Car createdCar = carRepository.save(carToCreate);

        // Возвращаем ответ
        return new CarResponse(
                createdCar.getCarId(),
                createdCar.getVin(),
                createdCar.getModel(),
                createdCar.getColor(),
                createdCar.getYearOfProduction(),
                createdCar.getEngine(),
                createdCar.getMileage(),
                new ProducerResponse(
                        producer.getProducerId(),
                        producer.getName(),
                        producer.getPhoneNumber(),
                        producer.getEmail()
                )
        );
    }
}

