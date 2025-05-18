package com.example.cardataproject.controller.aoi;

import com.example.cardataproject.dto.userDTO.UserResponse;
import com.example.cardataproject.dto.userDTO.UserUpdateRequestDto;
import com.example.cardataproject.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/admin")
public interface AdminApi {

    //* найти всех пользователей (полная информация - для ADMIN)
    @GetMapping("/full")
    public ResponseEntity<List<User>> findAllFullDetails();


    //* найти всех пользователей (ограниченная информация - для MANAGER)
    @GetMapping("/manager/all")
    public ResponseEntity<List<UserResponse>> findAll();


    // * обновить данные от имени пользователь (пользователь хочет
    // поменять какие-то данные в своем профиле)
    @PutMapping("/update")
    public ResponseEntity<UserResponse> updateUser(@RequestBody UserUpdateRequestDto request);


    //* удаление записи
    @DeleteMapping("/{id}")
    public boolean deleteUser(@PathVariable Integer id);


}
