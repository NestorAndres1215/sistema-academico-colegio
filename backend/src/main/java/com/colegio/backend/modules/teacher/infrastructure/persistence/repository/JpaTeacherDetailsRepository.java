package com.colegio.backend.modules.teacher.infrastructure.persistence.repository;

import com.colegio.backend.modules.teacher.infrastructure.persistence.entity.TeacherDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface JpaTeacherDetailsRepository
        extends JpaRepository<TeacherDetailsEntity, Long> {

    Optional<TeacherDetailsEntity> findByTeacher_Id(Long id);

    @Query("""
        SELECT td
        FROM TeacherDetailsEntity td
        JOIN FETCH td.teacher t
        WHERE t.id = :teacherId
    """)
    Optional<TeacherDetailsEntity> findByTeacherIdWithTeacher(
            @Param("teacherId") Long teacherId
    );


}
