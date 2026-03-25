package com.colegio.backend.application.dto.admin;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@Data
@ToString
public class AdministratorRequest {

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El formato del correo no es válido")
    @Size(max = 150, message = "El correo no debe exceder 150 caracteres")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, max = 100, message = "La contraseña debe tener entre 6 y 100 caracteres")
    private String password;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    private String firstName;

    @Size(max = 50, message = "El segundo nombre no debe exceder 50 caracteres")
    private String middleName;

    @NotBlank(message = "El apellido paterno es obligatorio")
    @Size(min = 2, max = 50, message = "El apellido paterno debe tener entre 2 y 50 caracteres")
    private String paternalLastName;

    @Size(max = 50, message = "El apellido materno no debe exceder 50 caracteres")
    private String maternalLastName;

    @NotNull(message = "La edad es obligatoria")
    private Integer age;

    @NotBlank(message = "El DNI es obligatorio")
    @Pattern(regexp = "^[0-9]{8}$", message = "El DNI debe tener 8 dígitos")
    private String dni;

    @NotBlank(message = "El teléfono es obligatorio")
    @Pattern(regexp = "^[0-9+\\- ]{7,15}$", message = "Teléfono inválido")
    private String phone;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @Past(message = "La fecha de nacimiento debe ser en el pasado")
    private LocalDate birthDate;

    @Size(max = 255, message = "El perfil no debe exceder 255 caracteres")
    private String profile;

    @NotBlank(message = "El género es obligatorio")
    private String gender;

    @NotBlank(message = "La nacionalidad es obligatoria")
    private String nationality;
}
