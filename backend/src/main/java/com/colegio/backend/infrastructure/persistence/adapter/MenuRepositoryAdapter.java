package com.colegio.backend.infrastructure.persistence.adapter;

import com.colegio.backend.domain.model.Menu;
import com.colegio.backend.domain.port.repository.MenuRepositoryPort;
import com.colegio.backend.infrastructure.persistence.mapper.MenuMapper;
import com.colegio.backend.infrastructure.persistence.repository.JpaMenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Component
@RequiredArgsConstructor
public class MenuRepositoryAdapter implements MenuRepositoryPort {

    private final JpaMenuRepository repository;
    private final MenuMapper mapper;

    @Override
    public List<Menu> findAll() {
        Set<String> visited = new HashSet<>();
        return repository.findAllWithChildren().stream()
                .map(menuEntity -> mapper.toDomain(menuEntity, visited))
                .toList();
    }
}
