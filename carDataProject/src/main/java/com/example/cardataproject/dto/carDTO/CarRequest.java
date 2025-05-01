package com.example.cardataproject.dto.carDTO;

import com.example.cardataproject.dto.producerDTO.ProducerRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarRequest {

    private Integer vin;

    @NotBlank(message = "model must be not blank")
    @Size(min = 1, max = 10, message = "model must be from 1 to 10 characters")
    private String model;

    @NotBlank(message = "color must be not blank")
    @Size(min = 3, max = 10, message = "color must be from 2 to 10 characters")
    private String color;

    private Integer yearOfProduction;
    private String engine;
    private Integer mileage;
    private ProducerRequest producerRequest;

}
