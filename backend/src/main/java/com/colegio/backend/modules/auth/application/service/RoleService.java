package com.colegio.backend.modules.auth.application.service;

import com.colegio.backend.modules.auth.application.dto.RoleResponse;
import com.colegio.backend.modules.auth.application.mapper.RoleMapper;
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
    private final RoleMapper roleMapper;

    @Override
    public Page<RoleResponse> getAll(String search, Pageable pageable) {
        return repositoryPort
                .getAll(search, pageable)
                .map(roleMapper::toResponse);
    }

    @Override
    public RoleResponse findByName(String name) {
        return repositoryPort.findByName(name)
                .map(roleMapper::toResponse)
                .orElseThrow(() -> new NotFoundException("Rol no encontrado"));
    }

    @Override
    public RoleResponse findById(Long id) {
        return repositoryPort.findById(id)
                .map(roleMapper::toResponse)
                .orElseThrow(() -> new NotFoundException("Rol no encontrado"));
    }
}