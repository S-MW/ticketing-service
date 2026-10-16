package com.ticketing_service.security;

import com.ticketing_service.entities.User;
import com.ticketing_service.repositories.UserRepo;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepo userRepo;
    public CustomUserDetailsService(UserRepo userRepo) { this.userRepo = userRepo; }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
        String authority = "ROLE_" + user.getRole().getName().toUpperCase();

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .authorities(List.of(new SimpleGrantedAuthority(authority)))
                .disabled(!user.getIsEnabled() || user.getIsDeleted())
                .build();
    }
}