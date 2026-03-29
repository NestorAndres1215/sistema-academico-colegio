package com.colegio.backend.domain.port.usecases;

import com.colegio.backend.domain.model.Menu;

import java.util.List;

public interface MenuUseCase {

    List<Menu> findAll();
}
