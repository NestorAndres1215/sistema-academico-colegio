package com.colegio.backend.modules.user_history.application.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record UserHistoryResponse(
        Long id,
        String action,
        String detail,
        String module,
        LocalDate date,
        LocalTime time,
        String status,
        String username,
        String email
) {
}
