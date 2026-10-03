package com.colegio.backend.modules.teacher.infrastructure.persistence.repository;

import com.colegio.backend.modules.teacher.infrastructure.persistence.entity.TeacherDetailsEntity;
import com.colegio.backend.modules.teacher.infrastructure.persistence.entity.TeacherObservationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.Optional;

public interface JpaTeacherObservationRepository extends JpaRepository<TeacherObservationEntity, Long> {

    Optional<TeacherObservationEntity> findByTeacher_Id(Long id);

    @Query("""
        SELECT to
        FROM TeacherObservationEntity to
        JOIN to.teacher t
        WHERE (:teacherCode IS NULL OR :teacherCode = '' OR t.code = :teacherCode)
        AND (:startDate IS NULL OR to.observationDate >= :startDate)
        AND (:endDate IS NULL OR to.observationDate <= :endDate)
    """)
    Page<TeacherObservationEntity> findAllByFilters(
            @Param("teacherCode") String teacherCode,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            Pageable pageable
    );

}