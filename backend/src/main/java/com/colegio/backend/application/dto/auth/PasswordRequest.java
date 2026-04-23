package com.colegio.backend.application.dto.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PasswordRequest {

    @NotBlank(message = "Current password is required")
    private String currentPassword;

    @NotBlank(message = "New password is required")
    private String newPassword;

    @NotBlank(message = "Confirm password is required")
    private String confirmNewPassword;
}
