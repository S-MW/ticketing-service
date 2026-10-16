package com.ticketing_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.Instant;


@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class UserResponseDto {

    private String name;
    private String email;
    private String roleName;

}
