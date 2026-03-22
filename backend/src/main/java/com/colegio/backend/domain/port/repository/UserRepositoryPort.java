package com.colegio.backend.domain.port.repository;

import com.colegio.backend.domain.model.User;

import java.util.Optional;

public interface UserRepositoryPort {

    Optional<User> findByEmail(String email);
}
