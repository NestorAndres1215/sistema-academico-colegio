package com.colegio.backend.application.service;

import com.colegio.backend.domain.exception.ResourceNotFoundException;
import com.colegio.backend.domain.model.Role;
import com.colegio.backend.domain.port.repository.RoleRepositoryPort;
import com.colegio.backend.domain.port.usecases.RoleUseCase;
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
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));
    }

    @Override
    public Role findById(String id) {
        return repositoryPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id not found"));
    }
}
