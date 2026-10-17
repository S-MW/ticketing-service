package com.ticketing_service.mappers;

import com.ticketing_service.dto.LoginHistoryLogDto;
import com.ticketing_service.dto.TicketStatusLogDto;
import com.ticketing_service.entities.LoginHistoryLog;
import com.ticketing_service.entities.TicketStatusLog;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LogMapper {


    List<TicketStatusLogDto> toTicketStatusLogDtoList(List<TicketStatusLog> logs);

    List<LoginHistoryLogDto> toLoginHistoryLogDtoList(List<LoginHistoryLog> logs);


}
