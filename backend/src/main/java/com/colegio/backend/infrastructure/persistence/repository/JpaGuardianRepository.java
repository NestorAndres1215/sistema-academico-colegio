package com.colegio.backend.infrastructure.persistence.repository;

import com.colegio.backend.infrastructure.persistence.entity.GuardianEntity;
import com.colegio.backend.infrastructure.persistence.entity.TeacherEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JpaGuardianRepository extends JpaRepository<GuardianEntity,String> {

    @Query("SELECT MAX(c.id) FROM GuardianEntity c")
    String findLastCode();



}
