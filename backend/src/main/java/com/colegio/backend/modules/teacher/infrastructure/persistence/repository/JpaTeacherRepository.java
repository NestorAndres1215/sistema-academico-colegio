package com.colegio.backend.modules.teacher.infrastructure.persistence.repository;

import com.colegio.backend.modules.teacher.infrastructure.persistence.entity.TeacherEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface JpaTeacherRepository extends JpaRepository<TeacherEntity,Long> {

    @Query("""
        SELECT t
        FROM TeacherEntity t
        WHERE (
            :status IS NULL
            OR :status = ''
            OR t.status = :status
        )
        AND (
            :search IS NULL
            OR :search = ''
            OR LOWER(t.code) LIKE LOWER(CONCAT('%', :search, '%'))
            OR LOWER(t.firstName) LIKE LOWER(CONCAT('%', :search, '%'))
            OR LOWER(t.middleName) LIKE LOWER(CONCAT('%', :search, '%'))
            OR LOWER(t.paternalLastName) LIKE LOWER(CONCAT('%', :search, '%'))
            OR LOWER(t.maternalLastName) LIKE LOWER(CONCAT('%', :search, '%'))
            OR LOWER(t.dni) LIKE LOWER(CONCAT('%', :search, '%'))
        )
    """)
    Page<TeacherEntity> findByAllStatus(
            @Param("status") String status,
            @Param("search") String search,
            Pageable pageable
    );


    boolean existsByProfessionalLicenseNumber(String professionalLicenseNumber);

    boolean existsByDni(String dni);

    boolean existsByPhone(String phone);

    boolean existsByCode(String code);

}
