package com.colegio.backend.infrastructure.persistence.mapper;

import com.colegio.backend.domain.model.Menu;
import com.colegio.backend.infrastructure.persistence.entity.MenuEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class MenuMapper {

    private final RoleMapper roleMapper;

    public Menu toDomain(MenuEntity entity, Set<String> visitedIds) {
        if (entity == null || visitedIds.contains(entity.getId())) return null;

        visitedIds.add(entity.getId());

        return Menu.builder()
                .id(entity.getId())
                .code(entity.getCode())
                .name(entity.getName())
                .icon(entity.getIcon())
                .route(entity.getRoute())
                .menuOrder(entity.getMenuOrder())
                .category(entity.getCategory())
                .parent(toDomain(entity.getParent(), visitedIds))
                .children(entity.getChildren() != null
                        ? entity.getChildren().stream()
                        .map(child -> toDomain(child, visitedIds))
                        .toList()
                        : List.of())
                .roles(entity.getRoles() != null
                        ? entity.getRoles().stream()
                        .map(roleMapper::toDomain)
                        .toList()
                        : List.of())
                .build();
    }
    public MenuEntity toEntity(Menu menu) {
        if (menu == null) return null;

        return MenuEntity.builder()
                .id(menu.getId())
                .code(menu.getCode())
                .name(menu.getName())
                .icon(menu.getIcon())
                .route(menu.getRoute())
                .menuOrder(menu.getMenuOrder())
                .category(menu.getCategory())
                .parent(toEntity(menu.getParent()))
                .children(menu.getChildren() != null
                        ? menu.getChildren().stream()
                        .map(this::toEntity)
                        .toList()
                        : List.of())
                .roles(menu.getRoles() != null
                        ? menu.getRoles().stream()
                        .map(roleMapper::toEntity)
                        .toList()
                        : List.of())
                .build();
    }
}