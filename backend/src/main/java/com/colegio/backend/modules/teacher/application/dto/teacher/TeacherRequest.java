package com.colegio.backend.modules.teacher.application.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public record TeacherRequest(

        @NotBlank(message = "El primer nombre es obligatorio")
        @Size(max = 50, message = "El primer nombre no puede superar los 50 caracteres")
        String firstName,

        @Size(max = 50, message = "El segundo nombre no puede superar los 50 caracteres")
        String middleName,

        @NotBlank(message = "El apellido paterno es obligatorio")
        @Size(max = 50, message = "El apellido paterno no puede superar los 50 caracteres")
        String paternalLastName,

        @NotBlank(message = "El apellido materno es obligatorio")
        @Size(max = 50, message = "El apellido materno no puede superar los 50 caracteres")
        String maternalLastName,

        @NotBlank(message = "El correo es obligatorio")
        @Email(message = "El correo no tiene un formato válido")
        String email,

        @NotBlank(message = "El DNI es obligatorio")
        @Pattern(regexp = "\\d{8}", message = "El DNI debe tener exactamente 8 dígitos")
        String dni,

        @NotNull(message = "La fecha de nacimiento es obligatoria")
        @Past(message = "La fecha de nacimiento debe ser anterior a la fecha actual")
        LocalDate birthDate,

        @NotBlank(message = "El género es obligatorio")
        String gender,

        @NotBlank(message = "El estado civil es obligatorio")
        String maritalStatus,

        @NotBlank(message = "El teléfono es obligatorio")
        @Pattern(regexp = "\\d{9}", message = "El teléfono debe tener 9 dígitos")
        String phone,

        @NotBlank(message = "La dirección es obligatoria")
        String address,

        @NotBlank(message = "La especialidad es obligatoria")
        String specialty,

        @NotBlank(message = "El grado académico es obligatorio")
        String academicDegree,

        @NotBlank(message = "El número de colegiatura es obligatorio")
        String professionalLicenseNumber,

        @NotBlank(message = "La universidad es obligatoria")
        String university,

        @NotNull(message = "La fecha de graduación es obligatoria")
        LocalDate graduationDate,

        @NotNull(message = "Los años de experiencia son obligatorios")
        @Min(value = 0, message = "Los años de experiencia no pueden ser negativos")
        Integer yearsOfExperience,

        String notes,

        @NotBlank(message = "El tipo de contrato es obligatorio")
        String contractType,

        @NotNull(message = "La fecha de inicio es obligatoria")
        LocalDate startDate,

        LocalDate endDate,

        @NotBlank(message = "El cargo es obligatorio")
        String position,

        @NotNull(message = "Las horas semanales son obligatorias")
        @Min(value = 1, message = "Las horas semanales deben ser mayores a 0")
        @Max(value = 48, message = "Las horas semanales no pueden superar 48")
        Integer weeklyHours,

        @NotNull(message = "El salario es obligatorio")
        @DecimalMin(value = "0.0", inclusive = false, message = "El salario debe ser mayor a 0")
        BigDecimal salary


) {
}
