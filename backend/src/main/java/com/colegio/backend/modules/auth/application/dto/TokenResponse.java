package com.colegio.backend.modules.auth.application.dto;

public record TokenResponse(
        String message,
        String token,
        String user,
        String email,
        String role,
        String expiration
) {
}