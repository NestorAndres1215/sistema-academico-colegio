package com.colegio.backend.infrastructure.persistence.repository;

import com.colegio.backend.infrastructure.persistence.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JpaStudentRepository extends JpaRepository<StudentEntity,String> {

    @Query("SELECT MAX(c.id) FROM StudentEntity c")
    String findLastCode();
    
}
