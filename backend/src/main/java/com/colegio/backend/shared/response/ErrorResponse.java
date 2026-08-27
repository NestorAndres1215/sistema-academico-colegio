package com.colegio.backend.shared.response;

import java.time.Instant;

public record ErrorResponse(
        String error,
        String message,
        Instant timestamp,
        int status,
        String traceId
) {
}