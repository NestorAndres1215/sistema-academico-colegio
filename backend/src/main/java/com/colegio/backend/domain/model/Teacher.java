package com.colegio.backend.domain.model;

import com.colegio.backend.domain.enums.Gender;
import com.colegio.backend.domain.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {

    private String id;
    private String firstName;
    private String middleName;
    private String paternalLastName;
    private String maternalLastName;
    private Integer age;
    private String dni;
    private String phone;
    private LocalDate birthDate;
    private String profile;
    private Gender gender;
    private String nationality;
    private String specialization;

    private LocalDate hireDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Status status;
    private User user;

}
