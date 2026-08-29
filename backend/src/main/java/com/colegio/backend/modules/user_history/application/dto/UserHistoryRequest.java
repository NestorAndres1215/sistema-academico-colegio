package com.colegio.backend.modules.user_history.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserHistoryRequest(

        @NotBlank(message = "La acción es obligatoria")
        String action,

        @NotBlank(message = "El detalle es obligatorio")
        String detail,

        @NotBlank(message = "El módulo es obligatorio")
        String module,

        @NotBlank(message = "El correo es obligatorio")
        @Email(message = "El formato del correo no es válido")
        String email

) {
}