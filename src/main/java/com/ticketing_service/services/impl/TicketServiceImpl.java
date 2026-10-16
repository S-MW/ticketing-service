package com.ticketing_service.services.impl;

import com.ticketing_service.dto.TicketDto;
import com.ticketing_service.entities.Ticket;
import com.ticketing_service.enums.TicketStatusEnum;
import com.ticketing_service.mappers.TicketMapper;
import com.ticketing_service.repositories.TicketRepo;
import com.ticketing_service.repositories.UserRepo;
import com.ticketing_service.services.TicketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TicketServiceImpl implements TicketService {

    private final TicketRepo ticketRepo;
    private final TicketMapper ticketMapper;

    public TicketServiceImpl(TicketRepo ticketRepo,TicketMapper ticketMapper) {
        this.ticketRepo = ticketRepo;
        this.ticketMapper = ticketMapper;
    }

    @Override
    public TicketDto create(TicketDto ticketDto) {

        Ticket ticket = ticketMapper.toEntity(ticketDto);
        ticket.setStatus(TicketStatusEnum.NEW);
        ticket.setIsDeleted(Boolean.FALSE);

        return ticketMapper.toDto(ticketRepo.save(ticket));
    }
}
