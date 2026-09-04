package com.colegio.backend.modules.teacher.infrastructure.persistence.repository;

import com.colegio.backend.modules.teacher.infrastructure.persistence.entity.TeacherDetailsEntity;
import com.colegio.backend.modules.teacher.infrastructure.persistence.entity.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaTeacherDetailsRepository
        extends JpaRepository<TeacherDetailsEntity, Long> {

    Optional<TeacherDetailsEntity> findByTeacher_Id(Long id);
}
