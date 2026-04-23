package com.colegio.backend.infrastructure.persistence.entity;

import com.colegio.backend.domain.enums.Gender;
import com.colegio.backend.domain.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "guardian")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GuardianEntity {

    @Id
    private String id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "paternal_last_name")
    private String paternalLastName;

    @Column(name = "maternal_last_name")
    private String maternalLastName;

    private String dni;
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    private String relationships;

    private Status status;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;


    @OneToMany(mappedBy = "guardian")
    private List<StudentEntity> students;
}
