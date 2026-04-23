package com.colegio.backend.infrastructure.persistence.repository;

import com.colegio.backend.infrastructure.persistence.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface JpaCompanyRepository extends JpaRepository<CompanyEntity,String> {

    Optional<CompanyEntity> findByName(String name);

    @Query("SELECT MAX(c.id) FROM CompanyEntity c")
    String findLastCode();
    
}
