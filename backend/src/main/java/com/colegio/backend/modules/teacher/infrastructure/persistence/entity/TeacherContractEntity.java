package com.colegio.backend.modules.teacher.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "teacher_contract") // Contrato del profesor
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeacherContractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identificador del contrato

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "teacher_id")
    private TeacherEntity teacher; // Profesor asociado


    @Column(name = "contract_type")
    private String contractType; // Tipo de contrato

    @Column(name = "start_date")
    private LocalDate startDate; // Fecha de inicio

    @Column(name = "end_date")
    private LocalDate endDate; // Fecha de finalización

    private String position; // Cargo

    @Column(name = "weekly_hours")
    private Integer weeklyHours; // Horas semanales

    private BigDecimal salary; // Remuneración / salario

    private String status; // Estado del contrato

    @Column(name = "created_at")
    private LocalDateTime createdAt; // Fecha de creación

    @Column(name = "updated_at")
    private LocalDateTime updatedAt; // Fecha de actualización

}