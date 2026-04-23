package com.colegio.backend.application.dto.admin;

import com.colegio.backend.domain.enums.Gender;
import com.colegio.backend.domain.enums.Status;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

@Data
public class AdministratorRequest {

    @NotBlank(message = "Email is required")
    @Email(message = "The email format is not valid")
    @Size(max = 150, message = "The email must not exceed 150 characters")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 100, message = "The password must be between 6 and 100 characters")
    private String password;

    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 50, message = "The first name must be between 2 and 50 characters")
    private String firstName;

    @Size(max = 50, message = "The middle name must not exceed 50 characters")
    private String middleName;

    @NotBlank(message = "Paternal last name is required")
    @Size(min = 2, max = 50, message = "The paternal last name must be between 2 and 50 characters")
    private String paternalLastName;

    @Size(max = 50, message = "The maternal last name must not exceed 50 characters")
    private String maternalLastName;

    @NotNull(message = "Age is required")
    private Integer age;

    @NotBlank(message = "DNI is required")
    @Pattern(regexp = "^[0-9]{8}$", message = "The DNI must have 8 digits")
    private String dni;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[0-9+\\- ]{7,15}$", message = "Invalid phone number")
    private String phone;

    @NotNull(message = "Birth date is required")
    @Past(message = "The birth date must be in the past")
    private LocalDate birthDate;

    @Size(max = 255, message = "The profile must not exceed 255 characters")
    private String profile;

    @NotNull(message = "Gender is required")
    private Gender gender;

    @NotBlank(message = "Nationality is required")
    private String nationality;
}
