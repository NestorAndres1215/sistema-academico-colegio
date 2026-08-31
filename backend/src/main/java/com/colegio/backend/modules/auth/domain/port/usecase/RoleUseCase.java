package com.colegio.backend.modules.auth.domain.port.usecase;

import com.colegio.backend.modules.auth.application.dto.RoleResponse;
import com.colegio.backend.modules.auth.domain.model.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RoleUseCase {

    Page<RoleResponse> getAll(String search, Pageable pageable);

    RoleResponse findByName(String name);

    RoleResponse findById(Long id);

}