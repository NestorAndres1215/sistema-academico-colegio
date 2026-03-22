package com.colegio.backend.application.dto.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {

    @NotBlank(message = "EL CORREO ES OBLIGATORIO.")
    private String login;

    @NotBlank(message = "LA CONTRASEÑA ES OBLIGATORIA.")
    private String password;

}
