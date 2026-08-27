package com.colegio.backend.modules.menu.domain.port.repository;

import com.colegio.backend.modules.menu.domain.model.Menu;
import java.util.List;

public interface MenuRepositoryPort {
    List<Menu> findAll();
}
