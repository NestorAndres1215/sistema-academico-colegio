package com.colegio.backend.infrastructure.persistence.repository;

import com.colegio.backend.domain.enums.Status;
import com.colegio.backend.infrastructure.persistence.entity.TeacherFeedbackEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JpaTeacherFeedbackRepository extends JpaRepository<TeacherFeedbackEntity,String> {


    @Query("SELECT MAX(c.id) FROM TeacherFeedbackEntity c")
    String findLastCode();

    @Query("""
    SELECT tf FROM TeacherFeedbackEntity tf
    WHERE (:teacherName IS NULL OR 
           LOWER(CONCAT(
                tf.teacher.firstName, ' ',
                COALESCE(tf.teacher.middleName, ''), ' ',
                tf.teacher.paternalLastName, ' ',
                COALESCE(tf.teacher.maternalLastName, '')
           )) LIKE LOWER(CONCAT('%', :teacherName, '%')))
    AND (:period IS NULL OR tf.period = :period)
    AND (:status IS NULL OR tf.status = :status)
""")
    Page<TeacherFeedbackEntity> searchTeacherNameAndPeriodAndStatus(
            @Param("teacherName") String teacherName,
            @Param("period") String period,
            @Param("status") Status status,
            Pageable pageable
    );

    @Query("""
    SELECT tf FROM TeacherFeedbackEntity tf
    WHERE (:teacherId IS NULL OR tf.teacher.id = :teacherId)
    AND (:period IS NULL OR tf.period = :period)
""")
    Page<TeacherFeedbackEntity> searchTeacherAndPeriod(
            @Param("teacherId") String teacherId,
            @Param("period") String period,
            Pageable pageable
    );
}
