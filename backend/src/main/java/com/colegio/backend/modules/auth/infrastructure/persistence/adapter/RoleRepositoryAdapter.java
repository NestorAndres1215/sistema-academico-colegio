package com.colegio.backend.modules.auth.infrastructure.persistence.adapter;

import com.colegio.backend.modules.auth.domain.model.Role;
import com.colegio.backend.modules.auth.domain.port.repository.RoleRepositoryPort;
import com.colegio.backend.modules.auth.infrastructure.persistence.mapper.RoleMapperPersistence;
import com.colegio.backend.modules.auth.infrastructure.persistence.repository.JpaRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RoleRepositoryAdapter implements RoleRepositoryPort {

    private final JpaRoleRepository jpaRoleRepository;
    private  final RoleMapperPersistence roleMapperPersistence;

    @Override
    public Page<Role> getAll(String search, Pageable pageable) {
        return jpaRoleRepository.search(search, pageable)
                .map(roleMapperPersistence::toDomain);
    }

    @Override
    public Optional<Role> findByName(String name) {
        return jpaRoleRepository.findByName(name)
                .map(roleMapperPersistence::toDomain);
    }

    @Override
    public Optional<Role> findById(Long id) {
        return jpaRoleRepository.findById(id)
                .map(roleMapperPersistence::toDomain);
    }

}