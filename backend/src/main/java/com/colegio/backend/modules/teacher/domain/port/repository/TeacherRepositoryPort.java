package com.colegio.backend.modules.teacher.domain.port.repository;

import com.colegio.backend.modules.teacher.domain.model.Teacher;
import com.colegio.backend.modules.teacher.infrastructure.persistence.entity.TeacherEntity;

import java.util.Optional;

public interface TeacherRepositoryPort {

    Optional<Teacher> findById(Long id);

    Optional<Teacher> findByCode(String code);

    Optional<Teacher> findByDni(String dni);

    Optional<Teacher> findByProfessionalLicenseNumber(String professionalLicenseNumber);

    Optional<Teacher> findByUser_Email(String email);

    Optional<Teacher> findByUser_Username(String username);

    Teacher save (Teacher teacher);

    boolean existsByProfessionalLicenseNumber(String email);

    boolean existsByDni(String email);

    boolean existsByPhone(String email);

    boolean existsByCode(String email);

}
