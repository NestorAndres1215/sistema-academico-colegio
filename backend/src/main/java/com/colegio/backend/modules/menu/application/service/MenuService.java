package com.colegio.backend.modules.menu.application.service;

import com.colegio.backend.modules.menu.domain.model.Menu;
import com.colegio.backend.modules.menu.domain.port.repository.MenuRepositoryPort;
import com.colegio.backend.modules.menu.domain.port.usecase.MenuUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService implements MenuUseCase {

    private final MenuRepositoryPort menuRepositoryPort;

    @Override
    public List<Menu> findAll() {
        return menuRepositoryPort.findAll();
    }

}
