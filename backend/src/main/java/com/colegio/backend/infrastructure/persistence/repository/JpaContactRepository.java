package com.colegio.backend.infrastructure.persistence.repository;

import com.colegio.backend.infrastructure.persistence.entity.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JpaContactRepository extends JpaRepository<ContactEntity,String> {

    @Query("SELECT MAX(c.id) FROM ContactEntity c")
    String findLastCode();
    
}
