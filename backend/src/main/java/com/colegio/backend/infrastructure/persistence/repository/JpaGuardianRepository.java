package com.colegio.backend.infrastructure.persistence.repository;

import com.colegio.backend.infrastructure.persistence.entity.GuardianEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JpaGuardianRepository extends JpaRepository<GuardianEntity,String> {

    @Query("SELECT MAX(c.id) FROM GuardianEntity c")
    String findLastCode();

}
