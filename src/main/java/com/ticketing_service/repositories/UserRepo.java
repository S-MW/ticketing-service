package com.ticketing_service.repositories;

import com.ticketing_service.entities.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);


    @EntityGraph(attributePaths = "role")
    Optional<User> findByEmail(String email);
}
