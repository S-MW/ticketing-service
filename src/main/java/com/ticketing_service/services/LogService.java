package com.ticketing_service.services;


import com.ticketing_service.dto.LoginHistoryLogDto;
import com.ticketing_service.dto.TicketStatusLogDto;
import com.ticketing_service.enums.TicketStatusEnum;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LogService {

    List<TicketStatusLogDto> getTicketStatusLogs(Pageable pageable);

    List<LoginHistoryLogDto> getLoginHistoryLogs(Pageable pageable);

    void logLogin(String email, boolean success);

    void logTicketStatus(Long ticketId, TicketStatusEnum oldStatus, TicketStatusEnum newStatus, String changedBy);

}
