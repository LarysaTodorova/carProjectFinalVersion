package com.example.cardataproject.controller;

import com.example.cardataproject.dto.userDTO.UserRequest;
import com.example.cardataproject.dto.userDTO.UserResponse;
import com.example.cardataproject.service.userService.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/email")
public class UserController {

    private final UserService service;


    @PostMapping("/registration")
    public ResponseEntity<UserResponse> registration(@RequestBody @Valid UserRequest request) {
        UserResponse response = service.registerUser(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/confirmation")
    public ResponseEntity<String> confirmation(@RequestParam String code) {
        boolean confirmationResult = service.receiveConfirmationCode(code);
        if (confirmationResult) {
            return ResponseEntity.ok("Confirmation ok");
        } else {
            return ResponseEntity.ok("Invalid confirmation code");
        }
    }

}
