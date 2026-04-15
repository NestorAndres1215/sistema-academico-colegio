package com.colegio.backend.application.dto.teacher;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
@Data
public class TeacherRequest {

    @NotBlank(message = "Email is required")
    @Email(message = "The email format is not valid")
    @Size(max = 150, message = "The email must not exceed 150 characters")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 100, message = "The password must be between 6 and 100 characters")
    private String password;

    private String firstName;
    private String middleName;
    private String paternalLastName;
    private String maternalLastName;
    private Integer age;
    private String dni;
    private String phone;
    private LocalDate birthDate;
    private String profile;
    private String gender;
    private String nationality;
    private Boolean active;
    private String specialization;
    private String status;
    private LocalDate hireDate;
}
