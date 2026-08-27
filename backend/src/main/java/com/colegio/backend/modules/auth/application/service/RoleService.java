package com.colegio.backend.modules.auth.application.service;

import com.colegio.backend.modules.auth.domain.model.Role;
import com.colegio.backend.modules.auth.domain.port.repository.RoleRepositoryPort;
import com.colegio.backend.modules.auth.domain.port.usecase.RoleUseCase;
import com.colegio.backend.shared.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService implements RoleUseCase {

    private final RoleRepositoryPort repositoryPort;

    @Override
    public Page<Role> getAll(String search, Pageable pageable) {
        return repositoryPort.getAll(search, pageable);
    }

    @Override
    public Role findByName(String name) {
        return repositoryPort.findByName(name)
                .orElseThrow(() -> new NotFoundException("Rol no encontrado"));
    }

    @Override
    public Role findById(Long id) {
        return repositoryPort.findById(id)
                .orElseThrow(() -> new NotFoundException("Rol no encontrado"));
    }
}