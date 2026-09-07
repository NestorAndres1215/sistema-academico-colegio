package com.colegio.backend.modules.teacher.infrastructure.persistence.repository;

import com.colegio.backend.modules.teacher.infrastructure.persistence.entity.TeacherEntity;
import com.colegio.backend.modules.user.infrastructure.persistence.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
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


    @Query("""
        SELECT t
        FROM TeacherEntity t
        WHERE t.status = 'ACTIVE'
        AND (
            :search IS NULL
            OR :search = ''
            OR LOWER(t.code) LIKE LOWER(CONCAT('%', :search, '%'))
            OR LOWER(t.firstName) LIKE LOWER(CONCAT('%', :search, '%'))
            OR LOWER(t.middleName) LIKE LOWER(CONCAT('%', :search, '%'))
            OR LOWER(t.paternalLastName) LIKE LOWER(CONCAT('%', :search, '%'))
            OR LOWER(t.maternalLastName) LIKE LOWER(CONCAT('%', :search, '%'))
        )
        ORDER BY t.firstName ASC, t.paternalLastName ASC
    """)
    Page<TeacherEntity> searchActive(
            @Param("search") String search,
            Pageable pageable
    );

    @Query(value = """
                SELECT *
                FROM teacher
                WHERE status = 'ACTIVE'
                ORDER BY RAND()
                LIMIT :limit
            """, nativeQuery = true)
    List<TeacherEntity> findRandom(@Param("limit") Pageable limit);


    boolean existsByProfessionalLicenseNumber(String professionalLicenseNumber);

    boolean existsByDni(String dni);

    boolean existsByPhone(String phone);

    boolean existsByCode(String code);

}
