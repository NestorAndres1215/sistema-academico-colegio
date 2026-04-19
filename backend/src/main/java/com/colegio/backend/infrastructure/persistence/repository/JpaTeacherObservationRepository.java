package com.colegio.backend.infrastructure.persistence.repository;

import com.colegio.backend.domain.enums.Status;
import com.colegio.backend.infrastructure.persistence.entity.TeacherObservationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JpaTeacherObservationRepository extends JpaRepository<TeacherObservationEntity,String> {


    @Query("SELECT MAX(c.id) FROM TeacherObservationEntity c")
    String findLastCode();


    @Query("""
    SELECT tbs FROM TeacherObservationEntity tbs
    WHERE (:teacherId IS NULL OR tbs.teacher.id = :teacherId)
    AND (:status IS NULL OR tbs.status = :status)
    ORDER BY tbs.createdAt DESC
""")
    Page<TeacherObservationEntity> findByTeacherAndStatus(
            @Param("teacherId") String teacherId,
            @Param("status") Status status,
            Pageable pageable
    );
}
