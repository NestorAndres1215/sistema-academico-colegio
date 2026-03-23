package com.colegio.backend.infrastructure.persistence.repository;

import com.colegio.backend.domain.model.Role;
import com.colegio.backend.infrastructure.persistence.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface JpaRoleRepository extends JpaRepository<RoleEntity,String> {

    Optional<RoleEntity> findByName(String nombre);
}
