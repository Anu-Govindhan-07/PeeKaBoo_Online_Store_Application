package com.peekaboo.user.controller;

import com.peekaboo.user.dto.UserRegistrationRequestDto;
import com.peekaboo.user.dto.UserResponseDto;
import com.peekaboo.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> registerUser(@Valid @RequestBody UserRegistrationRequestDto request) {
        UserResponseDto response = userService.registerUser(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}