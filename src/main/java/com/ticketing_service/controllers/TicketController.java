package com.ticketing_service.controllers;


import com.ticketing_service.dto.ApiResponse;
import com.ticketing_service.dto.TicketDto;
import com.ticketing_service.services.TicketService;
import com.ticketing_service.validations.groups.CreateTicket;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }


//   TODO: get by id
//   TODO: get all


//    TODO: update  message by id
//    TODO: change status by id

    @PostMapping()
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<ApiResponse<TicketDto>> create(@Validated(CreateTicket.class) @RequestBody TicketDto ticketDto) {
        return new ResponseEntity<>(new ApiResponse<>("Ticket created successfully", ticketService.create(ticketDto)), HttpStatus.CREATED);
    }


}
