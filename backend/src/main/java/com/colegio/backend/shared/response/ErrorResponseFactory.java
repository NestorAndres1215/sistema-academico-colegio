package com.colegio.backend.shared.response;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
public class ErrorResponseFactory {

    public ErrorResponse create(String message, HttpStatus status) {
        return new ErrorResponse(
                status.getReasonPhrase(),
                message,
                Instant.now(),
                status.value(),
                UUID.randomUUID().toString()
        );
    }
}