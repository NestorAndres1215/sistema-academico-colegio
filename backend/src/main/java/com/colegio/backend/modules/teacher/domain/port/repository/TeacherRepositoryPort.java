package com.colegio.backend.modules.teacher.domain.port.repository;

import com.colegio.backend.modules.teacher.application.dto.TeacherResponse;
import com.colegio.backend.modules.teacher.domain.model.Teacher;
import com.colegio.backend.modules.user.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface TeacherRepositoryPort {

    Optional<Teacher> findById(Long id);

    Page<Teacher> findByAllStatus(String status, String search, Pageable pageable);

    List<Teacher> search(String search, int limit);

    List<Teacher> findRandom(int limit);

    Teacher save (Teacher teacher);

    boolean existsByProfessionalLicenseNumber(String email);

    boolean existsByDni(String email);

    boolean existsByPhone(String email);

    boolean existsByCode(String email);

}
