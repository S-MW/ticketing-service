package com.ticketing_service.services.impl;

import com.ticketing_service.dto.LoginHistoryLogDto;
import com.ticketing_service.dto.TicketStatusLogDto;
import com.ticketing_service.entities.LoginHistoryLog;
import com.ticketing_service.entities.TicketStatusLog;
import com.ticketing_service.enums.TicketStatusEnum;
import com.ticketing_service.mappers.LogMapper;
import com.ticketing_service.repositories.LoginHistoryLogRepo;
import com.ticketing_service.repositories.TicketStatusLogRepo;
import com.ticketing_service.services.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@Slf4j
public class LogServiceImpl implements LogService {

    private final TicketStatusLogRepo ticketStatusLogRepo;
    private final LoginHistoryLogRepo loginHistoryLogRepo;
    private final LogMapper logMapper;

    public LogServiceImpl(TicketStatusLogRepo ticketStatusLogRepo, LoginHistoryLogRepo loginHistoryLogRepo, LogMapper logMapper) {
        this.ticketStatusLogRepo = ticketStatusLogRepo;
        this.loginHistoryLogRepo = loginHistoryLogRepo;
        this.logMapper = logMapper;
    }

    @Override
    public List<TicketStatusLogDto> getTicketStatusLogs(Pageable pageable) {

        return logMapper.toTicketStatusLogDtoList(ticketStatusLogRepo.findAll(pageable).getContent());
    }

    @Override
    public List<LoginHistoryLogDto> getLoginHistoryLogs(Pageable pageable) {

        return logMapper.toLoginHistoryLogDtoList(loginHistoryLogRepo.findAll(pageable).getContent());
    }

    @Override
    public void logLogin(String email, boolean success) {

        loginHistoryLogRepo.save(new LoginHistoryLog(null, email, success, Instant.now()));
    }

    @Override
    public void logTicketStatus(Long ticketId, TicketStatusEnum oldStatus, TicketStatusEnum newStatus, String changedBy) {

        ticketStatusLogRepo.save(new TicketStatusLog(null, ticketId, oldStatus, newStatus, changedBy, Instant.now()));
    }
}
