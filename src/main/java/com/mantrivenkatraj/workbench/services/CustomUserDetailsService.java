package com.mantrivenkatraj.workbench.services;

import com.mantrivenkatraj.workbench.exceptions.EntityNotFoundException;
import com.mantrivenkatraj.workbench.repositories.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import com.mantrivenkatraj.workbench.entities.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

     private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        com.mantrivenkatraj.workbench.entities.User userEntity = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        String authority = userEntity.getRole().name();
        // FIX 2: Explicitly use Spring's User class with full package name
        return org.springframework.security.core.userdetails.User.builder()
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .authorities(Collections.singletonList(new SimpleGrantedAuthority(authority)))
                .accountLocked(userEntity.getAccountStatus() ==
                        com.mantrivenkatraj.workbench.entities.User.AccStatus.INACTIVE)
                .build();
    }
}