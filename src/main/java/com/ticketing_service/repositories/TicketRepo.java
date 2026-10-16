package com.ticketing_service.repositories;

import com.ticketing_service.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TicketRepo extends JpaRepository<Ticket, Long> {


}
