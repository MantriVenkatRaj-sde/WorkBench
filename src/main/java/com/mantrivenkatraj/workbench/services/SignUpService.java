package com.mantrivenkatraj.workbench.services;

import com.mantrivenkatraj.workbench.entities.User;
import com.mantrivenkatraj.workbench.exceptions.EntityAlreadyExists;
import com.mantrivenkatraj.workbench.records.SignUpRequest;
import com.mantrivenkatraj.workbench.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
public class SignUpService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public SignUpService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public void registerUser(@Valid SignUpRequest request) {

        if (userRepository.existsByUsername(request.username())) {
            throw new EntityAlreadyExists("Username");
        }

        if (userRepository.existsByEmail(request.email())) {
            throw new EntityAlreadyExists("Email");
        }

        User user = User.builder()
                .username(request.username())
                .password(passwordEncoder.encode(request.password()))
                .email(request.email())
                .accountStatus(User.AccStatus.ACTIVE)
                .build();

        userRepository.save(user);
    }


}
