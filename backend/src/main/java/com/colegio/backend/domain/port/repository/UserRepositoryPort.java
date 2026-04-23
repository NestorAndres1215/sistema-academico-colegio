package com.colegio.backend.domain.port.repository;

import com.colegio.backend.domain.enums.Status;
import com.colegio.backend.domain.model.User;
import java.util.List;
import java.util.Optional;

public interface UserRepositoryPort {

    Optional<User> findByEmail(String email);

    List<User> findByStatus(Status status);

    List<User> findByEmailAndStatus(String email, Status status);

    User save (User user);

    boolean existsByEmail(String email);

    Optional<User> findById(String id);

    String findLastCode();
}
