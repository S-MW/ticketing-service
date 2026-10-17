package com.ticketing_service.repositories;

import com.ticketing_service.entities.LoginHistoryLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginHistoryLogRepo extends JpaRepository<LoginHistoryLog, Long> {

}
