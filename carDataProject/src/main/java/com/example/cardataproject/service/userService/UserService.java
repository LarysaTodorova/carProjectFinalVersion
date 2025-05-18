package com.example.cardataproject.service.userService;

import com.example.cardataproject.dto.userDTO.UserRequest;
import com.example.cardataproject.dto.userDTO.UserResponse;
import com.example.cardataproject.dto.userDTO.UserUpdateRequestDto;
import com.example.cardataproject.entity.User;
import com.example.cardataproject.repository.UserRepository;
import com.example.cardataproject.service.exception.NotFoundException;
import com.example.cardataproject.service.util.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
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

    public UserResponse findUserById(Integer id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("User with ID = " + id + " not found"));

        return converter.toDto(user);
    }

    public UserResponse findUserByEmail(String email) {
        User user = repository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User with email: " + email + " not found"));

        return converter.toDto(user);
    }

    public List<User> findFullDetailUsers() {
        return repository.findAll();
    }

    public List<UserResponse> findAllUsers() {
        List<User> users = repository.findAll();
        List<UserResponse> responses = converter.fromUsers(users);

        return responses;
    }

    public UserResponse updateUser(UserUpdateRequestDto updateRequest) {

        if (updateRequest.getEmail() == null || updateRequest.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email must be provided to update user");
        }

        String userEmail = updateRequest.getEmail();

        // найдем пользователя по email
        User userByEmail = repository.findByEmail(userEmail)
                .orElseThrow(() -> new NotFoundException("User with email: " + userEmail + " not found"));

        // обновляем все доступные поля
        // мы заранее НЕ ЗНАЕМ, а какие именно поля пользователь захочет поменять
        // то есть в JSON (в теде запроса) будут находится ТОЛЬКО те поля (со значением)
        // которые пользователь хочет менять (не обязательно все)
        if (updateRequest.getFirstName() != null && !updateRequest.getFirstName().isBlank()) {
            userByEmail.setName(updateRequest.getFirstName());
        }

        if (updateRequest.getHashPassword() != null && !updateRequest.getHashPassword().isBlank()) {
            userByEmail.setConfirmationCode(updateRequest.getHashPassword());
        }

        // сохраняем (обновляем) пользователя
        repository.save(userByEmail);

        return converter.toDto(userByEmail);
        // или вручную создать UserResponseDto из данных, которые хранятся в userByEmail
    }

    public boolean deleteUser(Integer id){

        // проверим, что такой id существует
        // и если нет - то сразу возвращаем false и ничего даже не пытаемся удалить

        if (!repository.existsById(id)){
            return false;
        }

        // если существует, то
        // вариант 1 - удаляем сразу по id

        repository.deleteById(id);

        // вариант 2 - сперва найдем объект по этому номеру id

//        User userForDelete = repository.findById(id).get();
//
//        repository.delete(userForDelete);

        return true;

    }
}
