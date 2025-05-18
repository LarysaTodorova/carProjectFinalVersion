package com.example.cardataproject.controller;

import com.example.cardataproject.controller.aoi.UserApi;
import com.example.cardataproject.dto.userDTO.UserResponse;
import com.example.cardataproject.service.userService.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserSecurityController implements UserApi {

    private final UserService service;


    //*найти пользователя по ID
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findUserById(@PathVariable Integer id){
        return ResponseEntity.ok(service.findUserById(id));
    };

    //*найти пользователя по email
    @GetMapping()
    public ResponseEntity<UserResponse> findUserByEmail(@RequestParam String email){
        return ResponseEntity.ok(service.findUserByEmail(email));
    };


}
