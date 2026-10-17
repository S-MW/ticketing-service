package com.ticketing_service.mappers;


public interface BaseMapper<D, E> {
    D toDto(E entity);
    E toEntity(D dto);
}