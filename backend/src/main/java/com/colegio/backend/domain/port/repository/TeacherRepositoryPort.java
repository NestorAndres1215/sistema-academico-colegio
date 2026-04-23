package com.colegio.backend.domain.port.repository;

import com.colegio.backend.domain.enums.Status;
import com.colegio.backend.domain.model.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface TeacherRepositoryPort {


    Optional<Teacher> findById(String id);

    List<Teacher> findAll();

    Teacher save(Teacher teacher);

    String findLastCode();

    boolean existsByDni(String dni);

    boolean existsByPhone(String phone);

    Page<Teacher> getByStatus(Status status, String search, Pageable pageable);
}
