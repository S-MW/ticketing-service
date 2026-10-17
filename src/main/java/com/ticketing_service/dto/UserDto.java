package com.ticketing_service.dto;


import com.ticketing_service.entities.Role;
import com.ticketing_service.validations.groups.UserLogin;
import com.ticketing_service.validations.groups.UserSignUp;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(hidden = true)
    private Integer id;

    @Schema(description = "full name", example = "AHMED")
    @NotNull(groups = UserSignUp.class)
    @NotEmpty(groups = UserSignUp.class)
    private String name;

    @NotNull(groups = {UserSignUp.class, UserLogin.class})
    @NotEmpty(groups = {UserSignUp.class, UserLogin.class})
    @Schema(description = "unique email, used to login", example = "c2@gmail.com")
    @Email(groups = {UserSignUp.class, UserLogin.class})
    private String email;

    @NotNull(groups = {UserSignUp.class, UserLogin.class})
    @NotEmpty(groups = {UserSignUp.class, UserLogin.class})
    @Schema(description = "password", example = "pass1234")
    private String password;

    @Schema(hidden = true)
    private Role role;

    @Schema(hidden = true)
    private String modifyBy;
    @Schema(hidden = true)
    private Instant modifyAt;
    @Schema(hidden = true)
    private String createdBy;
    @Schema(hidden = true)
    private Instant createAt;

    @Schema(hidden = true)
    private Boolean isDeleted;
    @Schema(hidden = true)
    private Boolean isEnabled;

    @Schema(description = "user role", allowableValues = {"Agent", "Customer", "Monitor"}, example = "Customer")
    @NotNull(groups = UserSignUp.class)
    @NotEmpty(groups = UserSignUp.class)
    private String role_name;

}
