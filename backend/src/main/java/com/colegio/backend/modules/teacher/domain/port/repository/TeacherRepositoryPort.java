package com.colegio.backend.modules.teacher.domain.port.repository;

import com.colegio.backend.modules.teacher.application.dto.TeacherResponse;
import com.colegio.backend.modules.teacher.domain.model.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface TeacherRepositoryPort {

    Optional<Teacher> findById(Long id);

    Page<Teacher> findByAllStatus(String status, String search, Pageable pageable);


    Teacher save (Teacher teacher);

    boolean existsByProfessionalLicenseNumber(String email);

    boolean existsByDni(String email);

    boolean existsByPhone(String email);

    boolean existsByCode(String email);

}
