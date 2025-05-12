package com.example.cardataproject.service.userService;

import com.example.cardataproject.dto.userDTO.UserRequest;
import com.example.cardataproject.dto.userDTO.UserResponse;
import com.example.cardataproject.entity.User;
import com.example.cardataproject.repository.UserRepository;
import com.example.cardataproject.service.util.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final EmailService emailService;
    private final Converter converter;

    public UserResponse registerUser(UserRequest request) {
        /*
        сгенерировать UUID код и его отправить
         */

        User newUser = converter.fromDto(request);
        newUser.setConfirmationCode(UUID.randomUUID().toString());
        newUser.setConfirmed(false);
        newUser.setData(LocalDateTime.now());

        repository.save(newUser);

        // отправляем email

        emailService.sendConfirmationCodeByEmail(newUser);

        return converter.toDto(newUser);
    }

    public boolean receiveConfirmationCode(String code) {
        User userByCode = repository.findByConfirmationCode(code)
                .orElseThrow(() -> new IllegalArgumentException("User with code " + code + " not found"));


        userByCode.setConfirmed(true);

        repository.save(userByCode);

        return true;
    }
}
