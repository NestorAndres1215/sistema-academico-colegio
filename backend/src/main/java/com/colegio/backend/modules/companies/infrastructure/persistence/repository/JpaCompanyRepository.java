package com.colegio.backend.modules.companies.infrastructure.persistence.repository;

import com.colegio.backend.modules.companies.domain.model.Company;
import com.colegio.backend.modules.companies.infrastructure.persistence.entity.CompanyEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface JpaCompanyRepository extends JpaRepository<CompanyEntity,Long> {

    @Query("""
        SELECT c
        FROM CompanyEntity c
        WHERE (
            :status IS NULL
            OR :status = ''
            OR c.status = :status
        )
        AND (
            :search IS NULL
            OR :search = ''
            OR LOWER(c.name) LIKE LOWER(CONCAT('%', :search, '%'))
            OR LOWER(c.code) LIKE LOWER(CONCAT('%', :search, '%'))
        )
    """)
    Page<CompanyEntity> searchByStatus(
            @Param("status") String status,
            @Param("search") String search,
            Pageable pageable
    );


    Optional<CompanyEntity> findByEmail(String email);

    Optional<CompanyEntity> findByCode(String code);

    List<CompanyEntity> findByStatus(String status);

    boolean existsByEmail(String email);

    boolean existsByCode(String code);

    boolean existsByTaxId(String code);

}
