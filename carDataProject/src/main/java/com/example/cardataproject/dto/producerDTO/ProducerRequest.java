package com.example.cardataproject.dto.producerDTO;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProducerRequest {

    @NotBlank(message = "Producer name must be not blank")
    @Size(min = 2, max = 10, message = "Producer name must be from 2 to 10 characters")
    @Pattern(regexp = "[A-Za-z0-9]+", message = "Producer name can contain latin character or digital only")
    private String name;
    private String phoneNumber;

    @NotBlank(message = "Email must be not blank")
    @Email(message = "Incorrect email format")
    private String email;
    private String password;
}
