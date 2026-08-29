package com.colegio.backend.modules.menu.infrastructure.persistence.adapter;

import com.colegio.backend.modules.menu.domain.model.Menu;
import com.colegio.backend.modules.menu.domain.port.repository.MenuRepositoryPort;
import com.colegio.backend.modules.menu.infrastructure.persistence.mapper.MenuMapperPersistence;
import com.colegio.backend.modules.menu.infrastructure.persistence.repository.JpaMenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class MenuRepositoryAdapter implements MenuRepositoryPort {

    private final JpaMenuRepository jpaMenuRepository;
    private final MenuMapperPersistence menuMapperPersistence;

    @Override
    public List<Menu> findAllWithChildren() {
        Set<Long> visited = new HashSet<>();
        return jpaMenuRepository.findAllWithChildren().stream()
                .map(menuEntity ->
                        menuMapperPersistence.toDomain(menuEntity, visited)
                )
                .toList();
    }
}