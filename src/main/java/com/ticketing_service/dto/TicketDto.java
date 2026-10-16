package com.ticketing_service.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ticketing_service.enums.TicketStatusEnum;
import com.ticketing_service.validations.groups.CreateTicket;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.Instant;


@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class TicketDto {

    private Long id;

    @NotNull(groups = CreateTicket.class)
    @NotEmpty(groups = CreateTicket.class)
    @Size(max = 250, groups = CreateTicket.class)
    @Pattern(
            regexp = "^[\\p{L}\\s]+$",
            message = "text can not contain symbols or numbers",
            groups = CreateTicket.class
    )
    private String text;

    private TicketStatusEnum Status;

    private String modifyBy;
    private Instant modifyAt;
    private String createdBy;
    private Instant createAt;

    @JsonIgnore()
    private Boolean isDeleted;


}
