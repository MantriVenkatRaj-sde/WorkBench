package com.mantrivenkatraj.workbench.records;

import jakarta.validation.constraints.*;


public record SignUpRequest(
        @NotBlank(message = "Username is required")
        String username,

        @NotBlank(message = "Password is required")
        @Size(min = 8, message = "Password must be at least 8 characters long")
        String password,

        @NotBlank(message = "Email is required")
        @Email(message = "Email should be valid")
        String email,

        @NotBlank(message = "Phone is required")
        @Pattern(
                regexp = "^\\+[1-9]\\d{7,14}$",
                message = "Phone number must be in international format (e.g. +919876543210)")
        String phone,
        @PositiveOrZero(message = "Your age is mentioned")
        int age
) {}
