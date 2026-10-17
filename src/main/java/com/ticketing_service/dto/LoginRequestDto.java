package com.ticketing_service.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDto {

    @NotNull
    @NotEmpty
    @Email
    @Schema(description = "registered email", example = "c2@gmail.com")
    private String email;

    @NotNull
    @NotEmpty
    @Schema(description = "password", example = "pass1234")
    private String password;

}
