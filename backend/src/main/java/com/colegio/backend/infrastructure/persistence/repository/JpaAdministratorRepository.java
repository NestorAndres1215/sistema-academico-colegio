package com.colegio.backend.infrastructure.persistence.repository;

import com.colegio.backend.infrastructure.persistence.entity.AdministratorEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface JpaAdministratorRepository  extends JpaRepository<AdministratorEntity,String> {

    List<AdministratorEntity> findByGender(String gender);

    List<AdministratorEntity> findByFirstName(String firstName);

    List<AdministratorEntity> findByFirstNameAndPaternalLastName(String firstName, String paternalLastName);

    List<AdministratorEntity> findByFirstNameAndPaternalLastNameAndMaternalLastName(
            String firstName, String paternalLastName, String maternalLastName);

    Optional<AdministratorEntity> findByDni(String dni);

    List<AdministratorEntity> findByPhone(String phone);

    @Query("SELECT MAX(c.id) FROM AdministratorEntity c")
    String findLastCode();

    boolean existsByDni(String dni);

    boolean existsByPhone(String phone);

    @Query("""
    SELECT a FROM AdministratorEntity a
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
    Page<AdministratorEntity> searchByStatus(
            @Param("status") boolean status,
            @Param("search") String search,
            Pageable pageable
    );
}
