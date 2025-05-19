package com.example.cardataproject.service.util;

import com.example.cardataproject.dto.userDTO.UserRequest;
import com.example.cardataproject.dto.userDTO.UserResponse;
import com.example.cardataproject.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Converter {

    public User fromDto(UserRequest request) {
        return User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .confirmationCode(request.getConfirmationCode())
                .build();
    }

    public UserResponse toDto(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole().toString())
                .data(user.getData())
                .build();
    }

    public List<UserResponse> fromUsers(List<User> users) {
        return users.stream()
                .map(currentUser -> toDto(currentUser))
                .toList();
    }
}
