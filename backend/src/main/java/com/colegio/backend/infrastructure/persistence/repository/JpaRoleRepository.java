package com.colegio.backend.infrastructure.persistence.repository;

import com.colegio.backend.infrastructure.persistence.entity.AdministratorEntity;
import com.colegio.backend.infrastructure.persistence.entity.RoleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface JpaRoleRepository extends JpaRepository<RoleEntity,String> {

    Optional<RoleEntity> findByName(String name);

    @Query("""
    SELECT a FROM RoleEntity a
    WHERE (:search IS NULL OR :search = ''
        OR LOWER(a.name) LIKE LOWER(CONCAT('%', :search, '%'))
    )
""")
    Page<RoleEntity> search(@Param("search") String search, Pageable pageable);
    
}
