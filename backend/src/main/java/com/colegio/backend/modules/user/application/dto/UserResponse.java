package com.colegio.backend.modules.user.application.dto;

public record UserResponse(
        Long id,
        String username,
        String email,
        String status,
        String role
) {

}