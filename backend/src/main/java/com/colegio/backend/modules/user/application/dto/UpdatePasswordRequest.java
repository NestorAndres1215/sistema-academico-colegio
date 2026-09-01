package com.colegio.backend.modules.user.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdatePasswordRequest(
        @NotBlank(message = "La nueva contraseña es obligatoria")
        @Size(
                min = 8,
                max = 100,
                message = "La contraseña debe tener entre 8 y 100 caracteres"
        )
        String newPassword,

        @NotBlank(message = "La confirmación de la contraseña es obligatoria")
        String confirmNewPassword
) {
}
