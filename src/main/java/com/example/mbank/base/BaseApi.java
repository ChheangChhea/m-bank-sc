package com.example.mbank.base;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record BaseApi<T>(Boolean isSuccess,
                         Integer code,
                         String massage,
                         LocalDateTime dateTime,
                         T payload
                         ) {
}
