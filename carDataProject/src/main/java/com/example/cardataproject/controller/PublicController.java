package com.example.cardataproject.controller;

import com.example.cardataproject.controller.api.PublicApi;
import com.example.cardataproject.dto.userDTO.UserRequest;
import com.example.cardataproject.dto.userDTO.UserResponse;
import com.example.cardataproject.service.userService.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/public")
public class PublicController implements PublicApi {

    private final UserService service;


    //*  добавить нового пользователя

    @PostMapping("/new")
    public ResponseEntity<UserResponse> addNewUser(@RequestBody UserRequest request){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.registrationSecurity(request));
    };


}
