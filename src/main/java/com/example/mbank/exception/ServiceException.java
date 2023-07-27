package com.example.mbank.exception;

import com.example.mbank.base.BaseError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ServiceException {
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ResponseStatusException.class)
    public BaseError<?> handleServiceException(ResponseStatusException e) {

        return BaseError.builder()
                .isSuccess(false)
                .message("Server has been errored, please check!")
                .code(HttpStatus.NOT_FOUND.value())
                .timestamp(LocalDateTime.now())
                .errors(e.getReason())
                .build();


    }
}