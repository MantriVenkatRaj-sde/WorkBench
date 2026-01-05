package com.mantrivenkatraj.workbench.jwt;

import com.mantrivenkatraj.workbench.entities.Member;
import com.mantrivenkatraj.workbench.entities.User;
import com.mantrivenkatraj.workbench.records.SignUpRequest;
import com.mantrivenkatraj.workbench.repositories.UserRepository;
import com.mantrivenkatraj.workbench.services.SignUpService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminSeeder {

    private final UserRepository userRepository;
    private final SignUpService signUpService;

    public AdminSeeder(UserRepository userRepository,
                       SignUpService signUpService) {
        this.userRepository = userRepository;
        this.signUpService = signUpService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void createAdmin() {

        if (userRepository.existsByUsername("Annie")) {
            return;
        }

        SignUpRequest adminRequest = new SignUpRequest(
                "Annie",
                "password123",
                "Annie@workbench.com",
                "+919933494886",
                21,
                Member.Gender.FEMALE
        );

        signUpService.createUser(adminRequest, User.Role.ADMIN);
    }
}


