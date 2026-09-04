package com.colegio.backend.modules.teacher.infrastructure.persistence.repository;

import com.colegio.backend.modules.teacher.infrastructure.persistence.entity.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface JpaTeacherRepository extends JpaRepository<TeacherEntity,Long> {

    Optional<TeacherEntity> findByCode(String code);

    Optional<TeacherEntity> findByDni(String dni);

    Optional<TeacherEntity> findByProfessionalLicenseNumber(String professionalLicenseNumber);

    Optional<TeacherEntity> findByUser_Email(String email);

    Optional<TeacherEntity> findByUser_Username(String username);

    boolean existsByProfessionalLicenseNumber(String professionalLicenseNumber);

    boolean existsByDni(String dni);

    boolean existsByPhone(String phone);

    boolean existsByCode(String code);

}
