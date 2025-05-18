package com.example.cardataproject.controller;

import com.example.cardataproject.controller.aoi.AdminApi;
import com.example.cardataproject.dto.userDTO.UserResponse;
import com.example.cardataproject.dto.userDTO.UserUpdateRequestDto;
import com.example.cardataproject.entity.User;
import com.example.cardataproject.service.userService.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController implements AdminApi {

    private final UserService service;


    //* найти всех пользователей (полная информация - для ADMIN)
    @GetMapping("/full")
    public ResponseEntity<List<User>> findAllFullDetails() {
        return ResponseEntity.ok(service.findFullDetailUsers());
    }


    //* найти всех пользователей (ограниченная информация - для MANAGER)
    @GetMapping("/manager/all")
    public ResponseEntity<List<UserResponse>> findAll() {
        return ResponseEntity.ok(service.findAllUsers());
    }


    @PutMapping("/update")
    public ResponseEntity<UserResponse> updateUser(@RequestBody UserUpdateRequestDto request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.updateUser(request));
    }


    //* удаление записи
    @DeleteMapping("/{id}")
    public boolean deleteUser(@PathVariable Integer id) {
        return service.deleteUser(id);
    }

}
