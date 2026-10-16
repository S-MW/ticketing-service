package com.ticketing_service.mappers;

import com.ticketing_service.dto.TicketDto;
import com.ticketing_service.entities.Ticket;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TicketMapper {


    Ticket toEntity(TicketDto ticketDto);

    TicketDto toDto(Ticket ticket);

    List<TicketDto> toDtoList(List<Ticket> tickets);


}


