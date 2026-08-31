package com.colegio.backend.modules.auth.application.mapper;

import com.colegio.backend.modules.auth.application.dto.RoleResponse;
import com.colegio.backend.modules.auth.domain.model.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {

    public RoleResponse toResponse(Role role) {
        return new RoleResponse(
                role.getId(),
                role.getName()
        );
    }
}