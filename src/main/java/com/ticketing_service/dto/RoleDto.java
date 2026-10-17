package com.ticketing_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;



@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class RoleDto {

    private Integer id;
    private String name;
    private Integer code;

    private Boolean isDeleted;
    private Boolean isEnabled;
}
