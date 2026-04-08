package com.colegio.backend.infrastructure.persistence.repository;

import com.colegio.backend.infrastructure.persistence.entity.PositionsEntity;
import com.colegio.backend.infrastructure.persistence.entity.RoleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface JpaPositionRepository extends JpaRepository<PositionsEntity,String> {

    Optional<PositionsEntity> findByName(String name);

    @Query("SELECT MAX(c.id) FROM PositionsEntity c")
    String findLastCode();

    @Query("""
    SELECT a FROM RoleEntity a
    WHERE (:search IS NULL OR :search = ''
        OR LOWER(a.name) LIKE LOWER(CONCAT('%', :search, '%'))
    )
""")
    Page<PositionsEntity> search(@Param("search") String search, Pageable pageable);

    
}
