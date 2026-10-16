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
public class TicketExpiryJob {

    private static final Duration EXPIRE_AFTER = Duration.ofSeconds(30);

    private final TicketRepo ticketRepo;

    public TicketExpiryJob(TicketRepo ticketRepo) {
        this.ticketRepo = ticketRepo;
    }

    @Scheduled(fixedRate = 5000)
    public void run() {

        Instant now = Instant.now();
        int expired = ticketRepo.updateNewTicketsToExpired(
                TicketStatusEnum.NEW, TicketStatusEnum.EXPIRED, now.minus(EXPIRE_AFTER), now);

        if (expired > 0) {
            log.info("TicketExpiryJob: expired {} ticket(s)", expired);
        }
    }
}
