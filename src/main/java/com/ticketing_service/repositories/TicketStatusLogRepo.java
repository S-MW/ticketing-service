package com.ticketing_service.repositories;

import com.ticketing_service.entities.TicketStatusLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketStatusLogRepo extends JpaRepository<TicketStatusLog, Long> {

}
