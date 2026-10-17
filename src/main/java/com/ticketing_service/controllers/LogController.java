package com.ticketing_service.controllers;


import com.ticketing_service.dto.LoginHistoryLogDto;
import com.ticketing_service.dto.TicketStatusLogDto;
import com.ticketing_service.services.LogService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
            @Parameter(description = "page number, starts from 0") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "rows per page") @RequestParam(defaultValue = "20") int size) {
        return ResponseEntity.ok(logService.getTicketStatusLogs(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"))));
    }

    @GetMapping("logins")
    public ResponseEntity<List<LoginHistoryLogDto>> getLoginHistoryLogs(
            @Parameter(description = "page number, starts from 0") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "rows per page") @RequestParam(defaultValue = "20") int size) {
        return ResponseEntity.ok(logService.getLoginHistoryLogs(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"))));
    }


}
