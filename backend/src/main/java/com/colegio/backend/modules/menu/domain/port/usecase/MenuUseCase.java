package com.colegio.backend.modules.menu.domain.port.usecase;

import com.colegio.backend.modules.menu.domain.model.Menu;
import java.util.List;

public interface MenuUseCase {
    List<Menu> findAllWithChildren();
}
