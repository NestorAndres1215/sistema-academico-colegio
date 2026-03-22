package com.colegio.backend.application.dto.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class ErrorResponse {
    private String error;
    private Object message;
    private LocalDateTime timestamp;
    private int status;
    private String traceId;
}
