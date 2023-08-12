package co.istad.mbank.api.auth1.web;

import jakarta.validation.constraints.NotBlank;

public record RefreshTokenDto(@NotBlank String refreshToken) {
}
