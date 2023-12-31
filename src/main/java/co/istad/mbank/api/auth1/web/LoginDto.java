package co.istad.mbank.api.auth1.web;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginDto(
        @NotBlank
        @Email
        String email,
        @NotBlank
        String password) {
}
