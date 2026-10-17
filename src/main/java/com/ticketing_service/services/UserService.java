package com.ticketing_service.services;


import com.ticketing_service.dto.LoginRequestDto;
import com.ticketing_service.dto.UserDto;
import com.ticketing_service.dto.UserLoginResponseDto;
import com.ticketing_service.dto.UserResponseDto;

public interface UserService {

    UserResponseDto register(UserDto userDto);

    UserLoginResponseDto login(LoginRequestDto loginRequestDto);

}
