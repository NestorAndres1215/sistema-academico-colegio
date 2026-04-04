package com.colegio.backend.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Administrator {
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
    private String gender;
    private String nationality;
    private User user;
    private Boolean status;


}
