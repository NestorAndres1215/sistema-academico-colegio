package com.colegio.backend.infrastructure.persistence.repository;

import com.colegio.backend.infrastructure.persistence.entity.TeacherEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface JpaTeacherRepository extends JpaRepository<TeacherEntity,String> {


    List<TeacherEntity> findByGender(String gender);

    List<TeacherEntity> findByFirstName(String firstName);

    List<TeacherEntity> findByFirstNameAndPaternalLastName(String firstName, String paternalLastName);

    List<TeacherEntity> findByFirstNameAndPaternalLastNameAndMaternalLastName(
            String firstName, String paternalLastName, String maternalLastName);

    Optional<TeacherEntity> findByDni(String dni);

    List<TeacherEntity> findByPhone(String phone);

    @Query("SELECT MAX(c.id) FROM TeacherEntity c")
    String findLastCode();

    boolean existsByDni(String dni);

    boolean existsByPhone(String phone);

    @Query("""
    SELECT a FROM TeacherEntity a
    WHERE a.status = :status
    AND (:search IS NULL OR :search = ''
        OR LOWER(a.firstName) LIKE LOWER(CONCAT('%', :search, '%'))
        OR LOWER(a.paternalLastName) LIKE LOWER(CONCAT('%', :search, '%'))
        OR LOWER(a.maternalLastName) LIKE LOWER(CONCAT('%', :search, '%'))
        OR LOWER(a.dni) LIKE LOWER(CONCAT('%', :search, '%'))
        OR LOWER(a.phone) LIKE LOWER(CONCAT('%', :search, '%'))
        OR LOWER(a.profile) LIKE LOWER(CONCAT('%', :search, '%'))
    )
""")
    Page<TeacherEntity> searchByStatus(
            @Param("status") boolean status,
            @Param("search") String search,
            Pageable pageable
    );

}
