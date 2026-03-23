package com.colegio.backend.infrastructure.persistence.repository;

import com.colegio.backend.infrastructure.persistence.entity.PositionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface JpaPositionRepository extends JpaRepository<PositionsEntity,String> {

    Optional<PositionsEntity> findByName(String name);

    @Query("SELECT MAX(c.id) FROM PositionsEntity c")
    String findLastCode();
}
