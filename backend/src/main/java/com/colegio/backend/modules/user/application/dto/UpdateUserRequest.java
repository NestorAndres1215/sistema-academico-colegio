package com.colegio.backend.modules.user.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateUserRequest(

        @NotBlank(message = "El nombre de usuario es obligatorio")
        @Size(
                min = 4,
                max = 50,
                message = "El nombre de usuario debe tener entre 4 y 50 caracteres"
        )
        String username,

        @NotBlank(message = "El correo electrónico es obligatorio")
        @Email(message = "El correo electrónico no es válido")
        String email,

        @NotBlank(message = "El rol es obligatorio")
        String role

) {
}