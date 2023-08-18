package co.istad.mbank.base;

import jakarta.validation.constraints.AssertFalse;
import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Builder
public record BaseApi<T>(Boolean isSuccess,
                         Integer code,
                         String message,
                         LocalDateTime dateTime,
                         T payload
                         ) {

    public static <T> BaseApiBuilder<T> ok() {
        return BaseApi.<T>builder()
                .isSuccess(true)
                .code(HttpStatus.OK.value());
//                .timestamp(LocalDateTime.now());
    }
}
