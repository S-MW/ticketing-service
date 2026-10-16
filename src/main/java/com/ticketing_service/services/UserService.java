package com.ticketing_service.services;


import com.ticketing_service.dto.UserDto;
import com.ticketing_service.dto.UserResponseDto;

public interface UserService {

    public UserResponseDto register(UserDto userDto);

}
