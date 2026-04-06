package com.colegio.backend.domain.port.usecases;

import com.colegio.backend.domain.model.Administrator;
import com.colegio.backend.domain.model.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface RoleUseCase {

    Page<Role> getAll( String search, Pageable pageable);

    Role findByName(String name);

    Role findById(String id);
}
