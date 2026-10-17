package com.ticketing_service.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.ticketing_service.enums.TicketStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketStatusLogDto {

    private Long id;
    private Long ticketId;
    private TicketStatusEnum oldStatus;
    private TicketStatusEnum newStatus;
    private String changedBy;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Riyadh")
    private Instant changedAt;

}
