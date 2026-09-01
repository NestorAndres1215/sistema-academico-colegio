package com.colegio.backend.modules.auth.application.dto;

import java.time.LocalDateTime;

public record VerificationCodeResponse(
        Long id,
        String verificationType,
        String verificationCode,
        LocalDateTime generatedAt,
        LocalDateTime expiresAt
) { }
