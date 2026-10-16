package com.ticketing_service.services.impl;

import com.ticketing_service.dto.UserDto;
import com.ticketing_service.dto.UserResponseDto;
import com.ticketing_service.entities.Role;
import com.ticketing_service.entities.User;
import com.ticketing_service.exceptions.ApiRequestException;
import com.ticketing_service.mappers.UserMapper;
import com.ticketing_service.repositories.RoleRepo;
import com.ticketing_service.repositories.UserRepo;
import com.ticketing_service.services.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final UserMapper userMapper;


    public UserServiceImpl(UserRepo userRepo, RoleRepo roleRepo, UserMapper userMapper) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.userMapper = userMapper;
    }

    @Override
    public UserResponseDto register(UserDto userDto) {


        // check if email is not already taken
        if (userRepo.existsByEmail(userDto.getEmail())) {
            throw new ApiRequestException(String.format("email ( %s ) is already exist", userDto.getEmail()));
        }

        // check if role is existing
        Role role = roleRepo.findByName(userDto.getRole_name()).orElseThrow(() -> new ApiRequestException("role not found"));
        userDto.setRole(role);

        // TODO: hash the password
//        userDto.setPassword("");


        User new_user = userMapper.toEntity(userDto);
        userRepo.save(new_user);

        UserResponseDto saved_user = userMapper.toResponse(new_user);

        return saved_user;


    }


}
