package com.ticketing_service.jobs;

import com.ticketing_service.enums.TicketStatusEnum;
import com.ticketing_service.repositories.TicketRepo;
import com.ticketing_service.services.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.List;


@Component
@Slf4j
public class TicketExpiryJob {

    private static final Duration EXPIRE_AFTER = Duration.ofSeconds(30);

    private final TicketRepo ticketRepo;
    private final LogService logService;

    public TicketExpiryJob(TicketRepo ticketRepo, LogService logService) {
        this.ticketRepo = ticketRepo;
        this.logService = logService;
    }

    @Scheduled(fixedRate = 5000)
    public void run() {

        Instant now = Instant.now();

        List<Long> ids = ticketRepo.findNewTicketIdsToExpire(TicketStatusEnum.NEW, now.minus(EXPIRE_AFTER));
        if (ids.isEmpty()) {
            return;
        }

        int expired = ticketRepo.updateNewTicketsToExpired(ids, TicketStatusEnum.NEW, TicketStatusEnum.EXPIRED, now);

        for (Long id : ids) {
            logService.logTicketStatus(id, TicketStatusEnum.NEW, TicketStatusEnum.EXPIRED, "SYSTEM:JOB");
        }

        log.info("TicketExpiryJob: expired {} ticket(s)", expired);
    }
}
