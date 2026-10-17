package com.ticketing_service.services.impl;

import com.ticketing_service.dto.TicketDto;
import com.ticketing_service.entities.Ticket;
import com.ticketing_service.enums.TicketStatusEnum;
import com.ticketing_service.exceptions.ApiRequestException;
import com.ticketing_service.mappers.TicketMapper;
import com.ticketing_service.repositories.TicketRepo;
import com.ticketing_service.services.LogService;
import com.ticketing_service.services.TicketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TicketServiceImpl implements TicketService {

    private final TicketRepo ticketRepo;
    private final TicketMapper ticketMapper;
    private final LogService logService;

    public TicketServiceImpl(TicketRepo ticketRepo, TicketMapper ticketMapper, LogService logService) {
        this.ticketRepo = ticketRepo;
        this.ticketMapper = ticketMapper;
        this.logService = logService;
    }

    @Override
    public TicketDto create(TicketDto ticketDto) {

        Ticket ticket = ticketMapper.toEntity(ticketDto);
        ticket.setStatus(TicketStatusEnum.NEW);
        ticket.setIsDeleted(Boolean.FALSE);

        Ticket savedTicket = ticketRepo.save(ticket);
        logService.logTicketStatus(savedTicket.getId(), null, TicketStatusEnum.NEW, savedTicket.getCreatedBy());

        return ticketMapper.toDto(savedTicket);
    }

    @Override
    public TicketDto getById(Long id) {

        Ticket ticket = ticketRepo.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new ApiRequestException("ticket not found"));

        return ticketMapper.toDto(ticket);
    }

    @Override
    public List<TicketDto> getAll() {

        return ticketMapper.toDtoList(ticketRepo.findAllByIsDeletedFalse());
    }

    @Override
    public TicketDto updateText(Long id, TicketDto ticketDto) {

        Ticket ticket = ticketRepo.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new ApiRequestException("ticket not found"));
        ticket.setText(ticketDto.getText());

        return ticketMapper.toDto(ticketRepo.save(ticket));
    }

    @Override
    public TicketDto resolveTicket(Long id) {

        Ticket ticket = ticketRepo.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new ApiRequestException("ticket not found"));


        if (ticket.getStatus() != TicketStatusEnum.NEW) {
            throw new ApiRequestException("only NEW tickets can be resolved");
        }
        ticket.setStatus(TicketStatusEnum.RESOLVED);

        Ticket savedTicket = ticketRepo.save(ticket);
        logService.logTicketStatus(savedTicket.getId(), TicketStatusEnum.NEW, TicketStatusEnum.RESOLVED, savedTicket.getModifyBy());

        return ticketMapper.toDto(savedTicket);
    }
}
