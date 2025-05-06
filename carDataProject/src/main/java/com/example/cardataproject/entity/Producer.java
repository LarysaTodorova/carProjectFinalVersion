package com.example.cardataproject.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "producers")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Producer {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer producerId;

    @NotBlank(message = "Producer name must be not blank")
    @Size(min = 2, max = 10, message = "Producer name must be from 2 to 10 characters")
    @Pattern(regexp = "[A-Za-z0-9]+", message = "Producer name can contain latin character or digital only")
    private String name;

    private String phoneNumber;

    @NotBlank(message = "Email must be not blank")
    @Email(message = "Incorrect email format")
    @Column(nullable = false, unique = true)
    private String email;

    private String password;

    public Producer(String name, String phoneNumber, String email, String password) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
    }
}
