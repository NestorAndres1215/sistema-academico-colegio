package com.colegio.backend.modules.menu.infrastructure.persistence.mapper;

import com.colegio.backend.modules.auth.domain.model.Role;
import com.colegio.backend.modules.auth.infrastructure.persistence.entity.RoleEntity;
import com.colegio.backend.modules.auth.infrastructure.persistence.mapper.RoleMapperPersistence;
import com.colegio.backend.modules.menu.domain.model.Menu;
import com.colegio.backend.modules.menu.infrastructure.persistence.entity.MenuEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MenuMapperPersistence {

    private final RoleMapperPersistence roleMapperPersistence;

    public Menu toDomain(MenuEntity menuEntity, Set<Long> visitedIds) {

        if (menuEntity == null || visitedIds.contains(menuEntity.getId())) {
            return null;
        }

        visitedIds.add(menuEntity.getId());

        return Menu.builder()
                .id(menuEntity.getId())
                .code(menuEntity.getCode())
                .name(menuEntity.getName())
                .icon(menuEntity.getIcon())
                .route(menuEntity.getRoute())
                .menuOrder(menuEntity.getMenuOrder())
                .category(menuEntity.getCategory())
                .parent(toDomain(menuEntity.getParent(), visitedIds))
                .children(toDomainChildren(menuEntity, visitedIds))
                .roles(toDomainRoles(menuEntity))
                .build();
    }

    public MenuEntity toEntity(Menu menu) {

        if (menu == null) {
            return null;
        }

        return MenuEntity.builder()
                .id(menu.getId())
                .code(menu.getCode())
                .name(menu.getName())
                .icon(menu.getIcon())
                .route(menu.getRoute())
                .menuOrder(menu.getMenuOrder())
                .category(menu.getCategory())
                .parent(toEntity(menu.getParent()))
                .children(toEntityChildren(menu))
                .roles(toEntityRoles(menu))
                .build();
    }

    private Set<Menu> toDomainChildren(MenuEntity menuEntity, Set<Long> visitedIds) {

        if (menuEntity.getChildren() == null) {
            return Set.of();
        }

        return menuEntity.getChildren()
                .stream()
                .map(child -> toDomain(child, visitedIds))
                .collect(Collectors.toSet());
    }

    private Set<MenuEntity> toEntityChildren(Menu menu) {

        if (menu.getChildren() == null) {
            return Set.of();
        }

        return menu.getChildren()
                .stream()
                .map(this::toEntity)
                .collect(Collectors.toSet());
    }

    private Set<Role> toDomainRoles(MenuEntity menuEntity) {

        if (menuEntity.getRoles() == null) {
            return Set.of();
        }

        return menuEntity.getRoles()
                .stream()
                .map(roleMapperPersistence::toDomain)
                .collect(Collectors.toSet());
    }

    private Set<RoleEntity> toEntityRoles(Menu menu) {

        if (menu.getRoles() == null) {
            return Set.of();
        }

        return menu.getRoles()
                .stream()
                .map(roleMapperPersistence::toEntity)
                .collect(Collectors.toSet());
    }
}