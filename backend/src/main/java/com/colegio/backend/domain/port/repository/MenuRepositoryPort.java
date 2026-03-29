package com.colegio.backend.domain.port.repository;

import com.colegio.backend.domain.model.Menu;

import java.util.List;

public interface MenuRepositoryPort {

    List<Menu> findAll();
}
