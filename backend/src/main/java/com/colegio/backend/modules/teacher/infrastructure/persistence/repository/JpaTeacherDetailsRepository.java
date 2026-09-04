package com.colegio.backend.modules.teacher.infrastructure.persistence.repository;

import com.colegio.backend.modules.teacher.infrastructure.persistence.entity.TeacherDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTeacherDetailsRepository extends JpaRepository<TeacherDetailsEntity,Long> {
}
