package com.ticketing_service.services;


import com.ticketing_service.dto.TicketDto;

import java.util.List;

public interface TicketService {

    TicketDto create(TicketDto ticketDto);

    TicketDto getById(Long id);

    List<TicketDto> getAll();

    TicketDto updateText(Long id, TicketDto ticketDto);

    TicketDto resolveTicket(Long id);

}
