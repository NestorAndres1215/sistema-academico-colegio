package com.colegio.backend.domain.port.repository;

import com.colegio.backend.domain.model.Administrator;
import com.colegio.backend.domain.model.User;
import com.colegio.backend.infrastructure.persistence.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryPort {

    Optional<User> findByEmail(String email);

    List<User> findByStatus(Boolean status);

    List<User> findByEmailAndStatus(String email, Boolean status);

    User save (User user);

    boolean existsByEmail(String email);

    Optional<User> findById(String id);

    String findLastCode();
}
