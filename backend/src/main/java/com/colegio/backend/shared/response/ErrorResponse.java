package com.colegio.backend.shared.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ErrorResponse {

    private final String error;
    private final String message;
    private final String timestamp;
    private final int status;
    private final String traceId;
}