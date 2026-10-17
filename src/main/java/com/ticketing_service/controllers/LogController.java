package com.ticketing_service.controllers;


import com.ticketing_service.dto.LoginHistoryLogDto;
import com.ticketing_service.dto.TicketStatusLogDto;
import com.ticketing_service.services.LogService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/logs")
@PreAuthorize("hasRole('ROLE_MONITOR')")
public class LogController {

    private final LogService logService;

    public LogController(LogService logService) {
        this.logService = logService;
    }


    @GetMapping("ticket-status")
    public ResponseEntity<List<TicketStatusLogDto>> getTicketStatusLogs(
            @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(logService.getTicketStatusLogs(pageable));
    }

    @GetMapping("logins")
    public ResponseEntity<List<LoginHistoryLogDto>> getLoginHistoryLogs(
            @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(logService.getLoginHistoryLogs(pageable));
    }


}
