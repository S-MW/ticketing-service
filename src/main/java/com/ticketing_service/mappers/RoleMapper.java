package com.ticketing_service.mappers;

import com.ticketing_service.dto.RoleDto;
import com.ticketing_service.entities.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper extends BaseMapper<RoleDto, Role> {


    @Override
    RoleDto toDto(Role entity);

    @Override
    Role toEntity(RoleDto dto);
}


