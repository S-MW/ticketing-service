package com.ticketing_service.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ticketing_service.enums.TicketStatusEnum;
import com.ticketing_service.validations.groups.CreateTicket;
import com.ticketing_service.validations.groups.UpdateTicket;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.Instant;


@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class TicketDto {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotNull(groups = {CreateTicket.class, UpdateTicket.class})
    @NotEmpty(groups = {CreateTicket.class, UpdateTicket.class})
    @Size(max = 250, groups = {CreateTicket.class, UpdateTicket.class})
    @Pattern(
            regexp = "^[\\p{L}\\s]+$",
            message = "text can not contain symbols or numbers",
            groups = {CreateTicket.class, UpdateTicket.class}
    )
    @Schema(description = "ticket text, letters and spaces only ( max 250 )", example = "my printer is not working")
    private String text;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty(value = "Status", access = JsonProperty.Access.READ_ONLY)
    private TicketStatusEnum status;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private String modifyBy;
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Riyadh")
    private Instant modifyAt;
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private String createdBy;
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Riyadh")
    private Instant createAt;

    @JsonIgnore()
    private Boolean isDeleted;


}
