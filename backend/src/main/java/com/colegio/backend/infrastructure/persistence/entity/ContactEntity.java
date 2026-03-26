package com.colegio.backend.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "contact")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactEntity {

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

    private Boolean estado;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private StudentEntity student;
}
