package com.colegio.backend.application.service;

import com.colegio.backend.domain.model.Menu;
import com.colegio.backend.domain.port.repository.MenuRepositoryPort;
import com.colegio.backend.domain.port.usecases.MenuUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService implements MenuUseCase {

    private  final MenuRepositoryPort repositoryPort;

    @Override
    public List<Menu> findAll() {
        return repositoryPort.findAll();
    }




}
