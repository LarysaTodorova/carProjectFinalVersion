package com.example.cardataproject.repository;

import com.example.cardataproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer>{

    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

    Optional<User> findByConfirmationCode(String confirmationCode);

}
