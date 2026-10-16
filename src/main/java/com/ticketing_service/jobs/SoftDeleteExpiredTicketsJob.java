package com.ticketing_service.jobs;

import com.ticketing_service.enums.TicketStatusEnum;
import com.ticketing_service.repositories.TicketRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;


@Component
@Slf4j
public class SoftDeleteExpiredTicketsJob {

    private static final Duration REMOVE_AFTER = Duration.ofMinutes(3);

    private final TicketRepo ticketRepo;

    public SoftDeleteExpiredTicketsJob(TicketRepo ticketRepo) {
        this.ticketRepo = ticketRepo;
    }

    @Scheduled(fixedRate = 10000)
    public void run() {

        Instant now = Instant.now();
        int removed = ticketRepo.SoftDeleteExpiredTickets(
                TicketStatusEnum.EXPIRED, now.minus(REMOVE_AFTER), now);

        if (removed > 0) {
            log.info("SoftDeleteExpiredTicketsJob: removed {} ticket(s)", removed);
        }
    }
}
