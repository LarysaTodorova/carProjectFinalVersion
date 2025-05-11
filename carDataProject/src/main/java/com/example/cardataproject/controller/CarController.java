package com.example.cardataproject.controller;


import com.example.cardataproject.dto.carDTO.CarRequest;
import com.example.cardataproject.dto.carDTO.CarResponse;
import com.example.cardataproject.service.carService.AddCarService;
import com.example.cardataproject.service.carService.DeleteCarService;
import com.example.cardataproject.service.carService.FindCarService;
import com.example.cardataproject.service.carService.UpdateCarService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
@RequiredArgsConstructor
public class CarController {

    private final AddCarService addCarService;
    private final DeleteCarService deleteCarService;
    private final FindCarService findCarService;
    private final UpdateCarService updateCarService;


    @PostMapping //localhost:8080/api/cars/carProducer
    public CarResponse createCar(@Valid @RequestBody CarRequest request) {
        return addCarService.createCar(request);
    }

    @GetMapping("/all") //localhost:8080/api/cars/all
    public List<CarResponse> findAllCars() {
        return findCarService.findAll();
    }

    @GetMapping("/producer") //localhost:8080/api/cars/producer?producerName=Audi
    public List<CarResponse> findByProducer(@RequestParam String producerName) {
        return findCarService.findByProducerName(producerName);
    }

    @GetMapping("/year") //localhost:8080/api/cars/year?year=2025
    public List<CarResponse> findByYear(@RequestParam Integer year) {
        return findCarService.findCarByYear(year);
    }

    @GetMapping("{id}") //localhost:8080/api/cars/1
    public CarResponse findById(@PathVariable Integer id) {
        return findCarService.findById(id);
    }

    @GetMapping("/vin")
    public CarResponse findBiVinCode(@RequestParam Integer vin) {
        return findCarService.findByVin(vin);
    }

    @GetMapping("/model") //localhost:8080/api/cars/model?model=A-7
    public List<CarResponse> findByModel(@RequestParam String model) {
        return findCarService.findCarByModel(model);
    }

    @DeleteMapping("{id}") //localhost:8080/api/cars/1
    public CarResponse deleteCar(@PathVariable Integer id) {
        return deleteCarService.deleteCarById(id);
    }

    @PutMapping("{id}") //localhost:8080/api/cars/1?mileage=10&color=red
    public CarResponse updateCar(@PathVariable Integer id,
                                 @RequestParam Integer mileage,
                                 @RequestParam String color) {
        return updateCarService.updateCar(id, mileage, color);
    }
}
