package com.colegio.backend.domain.port.repository;

import com.colegio.backend.domain.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleRepositoryPort {
    List<Role> findAll();

    Optional<Role> findByName(String name);

    Optional<Role> findById(String id);

}
