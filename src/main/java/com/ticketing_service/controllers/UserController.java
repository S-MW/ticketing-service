package com.ticketing_service.controllers;


import com.ticketing_service.dto.UserDto;
import com.ticketing_service.dto.UserResponseDto;
import com.ticketing_service.services.UserService;
import com.ticketing_service.validations.groups.UserSignUp;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("register")
    public ResponseEntity<UserResponseDto> register(@Validated(UserSignUp.class) @RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.register(userDto));
    }


}
