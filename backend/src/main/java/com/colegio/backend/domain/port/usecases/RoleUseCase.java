package com.colegio.backend.domain.port.usecases;

import com.colegio.backend.domain.model.Role;

import java.util.List;


public interface RoleUseCase {

    List<Role> findAll();

    Role findByName(String name);

    Role findById(String id);
}
