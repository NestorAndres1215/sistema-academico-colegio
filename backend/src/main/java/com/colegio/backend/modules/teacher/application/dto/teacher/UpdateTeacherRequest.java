package com.colegio.backend.modules.teacher.application.dto.teacher;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record UpdateTeacherRequest(

        @NotBlank(message = "El nombre es obligatorio")
        @Size(max = 100, message = "El nombre no puede superar los 100 caracteres")
        String firstName,

        @Size(max = 100, message = "El segundo nombre no puede superar los 100 caracteres")
        String middleName,

        @NotBlank(message = "El apellido paterno es obligatorio")
        @Size(max = 100, message = "El apellido paterno no puede superar los 100 caracteres")
        String paternalLastName,

        @NotBlank(message = "El apellido materno es obligatorio")
        @Size(max = 100, message = "El apellido materno no puede superar los 100 caracteres")
        String maternalLastName,

        @NotBlank(message = "El DNI es obligatorio")
        @Pattern(
                regexp = "\\d{8}",
                message = "El DNI debe tener exactamente 8 dígitos"
        )
        String dni,

        @NotNull(message = "La fecha de nacimiento es obligatoria")
        @Past(message = "La fecha de nacimiento debe ser anterior a la fecha actual")
        LocalDate birthDate,

        @NotBlank(message = "El género es obligatorio")
        String gender,

        @NotBlank(message = "El estado civil es obligatorio")
        String maritalStatus,

        @Pattern(
                regexp = "^\\d{9}$",
                message = "El teléfono debe tener 9 dígitos"
        )
        String phone,

        @Size(max = 255, message = "La dirección no puede superar los 255 caracteres")
        String address,

        @Size(max = 150, message = "La especialidad no puede superar los 150 caracteres")
        String specialty,

        @Size(max = 150, message = "El grado académico no puede superar los 150 caracteres")
        String academicDegree,

        @Size(max = 50, message = "El número de licencia profesional no puede superar los 50 caracteres")
        String professionalLicenseNumber

) {
}