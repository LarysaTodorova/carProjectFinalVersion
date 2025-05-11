package com.example.cardataproject.service.carService;

import com.example.cardataproject.dto.carDTO.CarResponse;
import com.example.cardataproject.dto.producerDTO.ProducerResponse;
import com.example.cardataproject.entity.Car;
import com.example.cardataproject.entity.Producer;
import com.example.cardataproject.repository.CarRepository;
import com.example.cardataproject.service.exception.NotFoundException;
import com.example.cardataproject.service.producerService.FindProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FindCarService {

    private final CarRepository carRepository;
    private final FindProducerService findProducerService;

    public List<CarResponse> findAll() {
        List<Car> allCars = carRepository.findAll();

        if (allCars.isEmpty()) {
            throw new NotFoundException("We have no any cars");
        }

        return allCars.stream()
                .map(currentCar -> {
                    Producer producer = currentCar.getProducer();

                    ProducerResponse producerResponse = new ProducerResponse(
                            producer.getProducerId(),
                            producer.getName(),
                            producer.getPhoneNumber(),
                            producer.getEmail()
                    );
                    return new CarResponse(
                            currentCar.getCarId(),
                            currentCar.getVin(),
                            currentCar.getModel(),
                            currentCar.getColor(),
                            currentCar.getYearOfProduction(),
                            currentCar.getEngine(),
                            currentCar.getMileage(),
                            producerResponse

                    );
                })
                .collect(Collectors.toList());
    }

    public CarResponse findById(Integer id) {
        Optional<Car> optionalCar = carRepository.findById(id);

        if (optionalCar.isEmpty()) {
            throw new NotFoundException("Car with id " + id + " not found");
        }

        Car foundedCar = optionalCar.get();

        Producer producer = foundedCar.getProducer();

        return new CarResponse(
                foundedCar.getCarId(),
                foundedCar.getVin(),
                foundedCar.getModel(),
                foundedCar.getColor(),
                foundedCar.getYearOfProduction(),
                foundedCar.getEngine(),
                foundedCar.getMileage(),
                new ProducerResponse(
                        producer.getProducerId(),
                        producer.getName(),
                        producer.getPhoneNumber(),
                        producer.getEmail()
                )
        );
    }

    public CarResponse findByVin(Integer id) {
        Optional<Car> optionalCar = carRepository.findByVin(id);

        if (optionalCar.isEmpty()) {
            throw new NotFoundException("Car with vin " + id + " not found");
        }

        Car foundedCar = optionalCar.get();

        Producer producer = foundedCar.getProducer();

        return new CarResponse(
                foundedCar.getCarId(),
                foundedCar.getVin(),
                foundedCar.getModel(),
                foundedCar.getColor(),
                foundedCar.getYearOfProduction(),
                foundedCar.getEngine(),
                foundedCar.getMileage(),
                new ProducerResponse(
                        producer.getProducerId(),
                        producer.getName(),
                        producer.getPhoneNumber(),
                        producer.getEmail()
                )
        );
    }


    public List<CarResponse> findByProducerName(String producerName) {
        // Получаем объект Producer
        Producer producer = findProducerService.getEntityByName(producerName);

        // Находим все машины, привязанные к этому продюсеру
        List<Car> cars = carRepository.findByProducer(producer);


        // Собираем ProducerResponse
        ProducerResponse producerResponse = new ProducerResponse(
                producer.getProducerId(),
                producer.getName(),
                producer.getPhoneNumber(),
                producer.getEmail()
        );

        // Мапим машины в CarResponse
        return cars.stream()
                .map(currentCar ->
                        new CarResponse(
                                currentCar.getCarId(),
                                currentCar.getVin(),
                                currentCar.getModel(),
                                currentCar.getColor(),
                                currentCar.getYearOfProduction(),
                                currentCar.getEngine(),
                                currentCar.getMileage(),
                                producerResponse

                        ))
                .collect(Collectors.toList());
    }


    public List<CarResponse> findCarByModel(String model) {
        List<Car> foundCars = carRepository.findByModel(model);

        if (foundCars.isEmpty()) {
            throw new NotFoundException("Car with model " + model + " not found");
        }

        return foundCars.stream()
                .map(currentCar -> new CarResponse(
                        currentCar.getCarId(),
                        currentCar.getVin(),
                        currentCar.getModel(),
                        currentCar.getColor(),
                        currentCar.getYearOfProduction(),
                        currentCar.getEngine(),
                        currentCar.getMileage(),
                        new ProducerResponse(
                                currentCar.getProducer().getProducerId(),
                                currentCar.getProducer().getName(),
                                currentCar.getProducer().getPhoneNumber(),
                                currentCar.getProducer().getEmail()
                        )
                ))
                .toList();
    }

    public List<CarResponse> findCarByYear(Integer year) {
        List<Car> foundCars = carRepository.findByYearOfProduction(year);

        if (foundCars.isEmpty()) {
            throw new NotFoundException("Car with year " + year + " not found");
        }

        return foundCars.stream()
                .map(currentCar -> new CarResponse(
                        currentCar.getCarId(),
                        currentCar.getVin(),
                        currentCar.getModel(),
                        currentCar.getColor(),
                        currentCar.getYearOfProduction(),
                        currentCar.getEngine(),
                        currentCar.getMileage(),
                        new ProducerResponse(
                                currentCar.getProducer().getProducerId(),
                                currentCar.getProducer().getName(),
                                currentCar.getProducer().getPhoneNumber(),
                                currentCar.getProducer().getEmail()
                        )
                ))
                .toList();
    }

}

