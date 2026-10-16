package com.ticketing_service.dto;


import com.ticketing_service.entities.Role;
import com.ticketing_service.validations.groups.UserLogin;
import com.ticketing_service.validations.groups.UserSignUp;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.Instant;


@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Integer id;

    @NotNull(groups = UserSignUp.class)
    @NotEmpty(groups = UserSignUp.class)
    private String name;

    @NotNull(groups = {UserSignUp.class, UserLogin.class})
    @NotEmpty(groups = {UserSignUp.class, UserLogin.class})
    @Email(groups = {UserSignUp.class, UserLogin.class})
    private String email;

    @NotNull(groups = {UserSignUp.class, UserLogin.class})
    @NotEmpty(groups = {UserSignUp.class, UserLogin.class})
    private String password;

    private Role role;

    private String modifyBy;
    private Instant modifyAt;
    private String createdBy;
    private Instant createAt;

    private Boolean isDeleted;
    private Boolean isEnabled;

    @NotNull(groups = UserSignUp.class)
    @NotEmpty(groups = UserSignUp.class)
    private String role_name;

}
