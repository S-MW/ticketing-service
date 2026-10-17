package com.ticketing_service.repositories;

import com.ticketing_service.entities.Ticket;
import com.ticketing_service.enums.TicketStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepo extends JpaRepository<Ticket, Long> {

    Optional<Ticket> findByIdAndIsDeletedFalse(Long id);

    List<Ticket> findAllByIsDeletedFalse();

    @Query("""
            SELECT t.id FROM Ticket t
            WHERE t.Status = :status AND t.isDeleted = false AND t.createAt <= :datetimeBefore30s
            """)
    List<Long> findNewTicketIdsToExpire(@Param("status") TicketStatusEnum status,
                                        @Param("datetimeBefore30s") Instant datetimeBefore30s);

    // keep "t.Status = :fromStatus" so a ticket resolved in the meantime is not overwritten
    @Modifying
    @Transactional
    @Query("""
            UPDATE Ticket t
            SET t.Status = :toStatus, t.modifyAt = :now, t.modifyBy = 'SYSTEM:JOB'
            WHERE t.id IN :ids AND t.Status = :fromStatus
            """)
    int updateNewTicketsToExpired(@Param("ids") List<Long> ids,
                                  @Param("fromStatus") TicketStatusEnum fromStatus,
                                  @Param("toStatus") TicketStatusEnum toStatus,
                                  @Param("now") Instant now);

    @Modifying
    @Transactional
    @Query("""
            UPDATE Ticket t
            SET t.isDeleted = true, t.modifyAt = :now, t.modifyBy = 'SYSTEM:JOB'
            WHERE t.Status = :status AND t.isDeleted = false AND t.modifyAt <= :datetimeBefore3m
            """)
    int SoftDeleteExpiredTickets(@Param("status") TicketStatusEnum status,
                                         @Param("datetimeBefore3m") Instant datetimeBefore3m,
                                         @Param("now") Instant now);

}
