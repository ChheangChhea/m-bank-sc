package com.example.mbank.base;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record BaseError<T>(Boolean isSuccess,
                           String message,
                           Integer code,
                           LocalDateTime timestamp,
                           T errors) {
}
