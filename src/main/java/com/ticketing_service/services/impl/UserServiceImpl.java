package com.ticketing_service.services.impl;

import com.ticketing_service.dto.UserDto;
import com.ticketing_service.dto.UserLoginResponseDto;
import com.ticketing_service.dto.UserResponseDto;
import com.ticketing_service.entities.Role;
import com.ticketing_service.entities.User;
import com.ticketing_service.exceptions.ApiRequestException;
import com.ticketing_service.mappers.UserMapper;
import com.ticketing_service.repositories.RoleRepo;
import com.ticketing_service.repositories.UserRepo;
import com.ticketing_service.security.JwtService;
import com.ticketing_service.services.LogService;
import com.ticketing_service.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final LogService logService;


    public UserServiceImpl(UserRepo userRepo, RoleRepo roleRepo, UserMapper userMapper, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager, LogService logService) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.logService = logService;
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

        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));

        User new_user = userMapper.toEntity(userDto);
        userRepo.save(new_user);

        return userMapper.toResponse(new_user);
    }

    @Override
    public UserLoginResponseDto login(UserDto userDto) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword()));
        } catch (AuthenticationException e) {
            log.error(e.getMessage());
            logService.logLogin(userDto.getEmail(), false);
            throw new ApiRequestException("invalid email or password");
        }
        logService.logLogin(authentication.getName(), true);
        String role = authentication.getAuthorities().iterator().next().getAuthority();
        return new UserLoginResponseDto(jwtService.generateToken(authentication.getName(), role));
    }


}
