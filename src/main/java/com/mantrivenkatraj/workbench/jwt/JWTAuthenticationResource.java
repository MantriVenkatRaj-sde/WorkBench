package com.mantrivenkatraj.workbench.jwt;

import com.mantrivenkatraj.workbench.records.SignUpRequest;
import com.mantrivenkatraj.workbench.services.SignUpService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
public class JWTAuthenticationResource {

    private final JwtEncoder jwtEncoder;
    private final AuthenticationConfiguration authenticationConfiguration;
    private final SignUpService signUpService;

    public JWTAuthenticationResource(
            JwtEncoder jwtEncoder,
            AuthenticationConfiguration authenticationConfiguration,
            SignUpService signUpService
    ) {
        this.jwtEncoder = jwtEncoder;
        this.authenticationConfiguration = authenticationConfiguration;
        this.signUpService = signUpService;
    }

    @GetMapping("/")
    public String statusCheck() {
        return "Status : Up";
    }

    @PostMapping("/authenticate")
    public JwtResponse authenticate(@RequestBody Request request) throws Exception {
        AuthenticationManager authenticationManager =
                authenticationConfiguration.getAuthenticationManager();

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );

        return new JwtResponse(createToken(authentication));
    }
    @GetMapping("/debug/token")
    public ResponseEntity<?> debugToken(Authentication authentication) {
        return ResponseEntity.ok(Map.of(
                "name", authentication.getName(),
                "authorities", authentication.getAuthorities(),
                "details", Objects.requireNonNull(authentication.getDetails())
        ));
    }


    @PostMapping("/signup")
    public ResponseEntity register(@RequestBody @Valid SignUpRequest request) {
        signUpService.registerUser(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Signed up successfully");
    }

    private String createToken(Authentication authentication) {
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(1800))
                .subject(authentication.getName())
                .claim("scope", createScope(authentication))
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    private String createScope(Authentication authentication) {
        return authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
    }

    record Request(String username, String password) {}
    record JwtResponse(String token) {}
}