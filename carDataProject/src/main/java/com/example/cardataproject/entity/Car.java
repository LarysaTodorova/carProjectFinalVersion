package com.example.cardataproject.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cars")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer carId;
    private Integer vin;

    @NotBlank(message = "model must not be blank")
    @Size(min = 1, max = 10, message = "model must be from 1 to 10 characters")
    private String model;

    @NotBlank(message = "color must not be blank")
    @Size(min = 3, max = 10, message = "color must be from 2 to 10 characters")
    private String color;

    private Integer yearOfProduction;
    private String engine;
    private Integer mileage;

    @ManyToOne
    @JoinColumn(name = "producer_producer_id")
    private Producer producer;


}
