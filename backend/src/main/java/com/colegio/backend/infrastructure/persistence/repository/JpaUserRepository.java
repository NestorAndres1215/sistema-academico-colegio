package com.colegio.backend.infrastructure.persistence.repository;

import com.colegio.backend.infrastructure.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<UserEntity,String> {

    Optional<UserEntity> findByEmail(String email);
}
