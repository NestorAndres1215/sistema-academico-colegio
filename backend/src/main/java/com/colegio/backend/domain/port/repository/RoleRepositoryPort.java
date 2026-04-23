package com.colegio.backend.domain.port.repository;

import com.colegio.backend.domain.model.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface RoleRepositoryPort {
    
    Page<Role> getAll(String search, Pageable pageable);

    Optional<Role> findByName(String name);

    Optional<Role> findById(String id);

}
