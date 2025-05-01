package com.example.cardataproject.dto.producerDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProducerResponse {

    private Integer producerId;
    private String name;
    private String phoneNumber;
    private String email;

}

