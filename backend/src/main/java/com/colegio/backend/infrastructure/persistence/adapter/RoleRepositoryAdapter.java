package com.colegio.backend.infrastructure.persistence.adapter;

import com.colegio.backend.domain.model.Role;
import com.colegio.backend.domain.port.repository.RoleRepositoryPort;
import com.colegio.backend.infrastructure.persistence.mapper.RoleMapper;
import com.colegio.backend.infrastructure.persistence.repository.JpaRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RoleRepositoryAdapter implements RoleRepositoryPort {

    private final JpaRoleRepository repository;
    private  final RoleMapper mapper;

    @Override
    public List<Role> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public Optional<Role> findByName(String name) {
        return repository.findByName(name).map(mapper::toDomain);
    }

    @Override
    public Optional<Role> findById(String id) {
        return repository.findById(id).map(mapper::toDomain);
    }
}
