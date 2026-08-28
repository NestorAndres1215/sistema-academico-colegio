package com.colegio.backend.modules.companies.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CompanyRequest(

        @NotBlank(message = "El código es obligatorio")
        @Size(max = 50, message = "El código no puede superar los 50 caracteres")
        String code,

        @NotBlank(message = "El nombre es obligatorio")
        @Size(max = 150, message = "El nombre no puede superar los 150 caracteres")
        String name,

        @NotBlank(message = "La razón social es obligatoria")
        @Size(max = 200, message = "La razón social no puede superar los 200 caracteres")
        String businessName,

        @NotBlank(message = "El RUC es obligatorio")
        @Size(max = 20, message = "El RUC no puede superar los 20 caracteres")
        String taxId,

        @NotBlank(message = "El correo electrónico es obligatorio")
        @Email(message = "El correo electrónico no es válido")
        @Size(max = 150, message = "El correo no puede superar los 150 caracteres")
        String email,

        @NotBlank(message = "El teléfono es obligatorio")
        @Size(max = 20, message = "El teléfono no puede superar los 20 caracteres")
        String phone,

        @NotBlank(message = "La dirección es obligatoria")
        @Size(max = 255, message = "La dirección no puede superar los 255 caracteres")
        String address,

        @NotBlank(message = "La ciudad es obligatoria")
        @Size(max = 100, message = "La ciudad no puede superar los 100 caracteres")
        String city,

        @NotBlank(message = "El país es obligatorio")
        @Size(max = 100, message = "El país no puede superar los 100 caracteres")
        String country,

        @Size(max = 255, message = "El sitio web no puede superar los 255 caracteres")
        String website
) {
}