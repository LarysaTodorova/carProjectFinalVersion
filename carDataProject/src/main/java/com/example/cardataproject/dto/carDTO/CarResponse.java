package com.example.cardataproject.dto.carDTO;

import com.example.cardataproject.dto.producerDTO.ProducerResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarResponse {

    private Integer carId;
    private Integer vin;
    private String model;
    private String color;
    private Integer yearOfProduction;
    private String engine;
    private Integer mileage;
    private ProducerResponse producerResponse;
}
