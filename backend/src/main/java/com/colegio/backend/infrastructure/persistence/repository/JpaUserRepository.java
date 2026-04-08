package com.colegio.backend.infrastructure.persistence.repository;

import com.colegio.backend.infrastructure.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<UserEntity,String> {

    Optional<UserEntity> findByEmail(String email);

    List<UserEntity> findByStatus(Boolean status);

    List<UserEntity> findByEmailAndStatus(String email, Boolean status);

    boolean existsByEmail(String email);

    @Query("SELECT MAX(c.id) FROM UserEntity c")
    String findLastCode();
    
}
