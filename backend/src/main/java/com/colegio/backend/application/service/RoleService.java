package com.colegio.backend.application.service;

import com.colegio.backend.domain.exception.ResourceNotFoundException;
import com.colegio.backend.domain.model.Role;
import com.colegio.backend.domain.port.repository.RoleRepositoryPort;
import com.colegio.backend.domain.port.usecases.RoleUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService implements RoleUseCase {

    private final RoleRepositoryPort repositoryPort;

    @Override
    public List<Role> findAll() {
        return repositoryPort.findAll();
    }

    @Override
    public Role findByName(String name) {
        return repositoryPort.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("ROL NO ENCONTRADO"));
    }

    @Override
    public Role findById(String id) {
        return repositoryPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CODIGO NO ENCONTRADO"));
    }
}
