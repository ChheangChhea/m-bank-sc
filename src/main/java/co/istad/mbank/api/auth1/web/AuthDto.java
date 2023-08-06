package co.istad.mbank.api.auth1.web;

import lombok.Builder;

@Builder
public record AuthDto(String tokenType,
                     String accessToken,
                      String refreshToken) {
}
