package com.colegio.backend.modules.teacher.infrastructure.persistence.repository;

import com.colegio.backend.modules.teacher.infrastructure.persistence.entity.TeacherContractEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;

public interface JpaTeacherContractRepository extends JpaRepository <TeacherContractEntity,Long>{
    Optional<TeacherContractEntity> findByTeacher_Id(Long id);

    @Query("""
        SELECT tc
        FROM TeacherContractEntity tc
        JOIN tc.teacher t
        WHERE (:teacherCode IS NULL OR :teacherCode = '' OR t.code = :teacherCode)
        AND (:startDate IS NULL OR tc.startDate >= :startDate)
        AND (:endDate IS NULL OR tc.endDate <= :endDate)
        AND (:status IS NULL OR :status = '' OR tc.status = :status)
    """)
    Page<TeacherContractEntity> findAllByFilters(
            @Param("teacherCode") String teacherCode,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("status") String status,
            Pageable pageable
    );


}
