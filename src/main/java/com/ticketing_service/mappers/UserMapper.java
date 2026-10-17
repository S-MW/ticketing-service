package com.ticketing_service.mappers;

import com.ticketing_service.dto.UserDto;
import com.ticketing_service.dto.UserResponseDto;
import com.ticketing_service.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "isEnabled", ignore = true)
    @Mapping(target = "isDeleted", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createAt", ignore = true)
    @Mapping(target = "modifyBy", ignore = true)
    @Mapping(target = "modifyAt", ignore = true)
    User toEntity(UserDto userDto);

    @Mapping(target = "roleName", source = "role.name")
    UserResponseDto toResponse(User user);

}


