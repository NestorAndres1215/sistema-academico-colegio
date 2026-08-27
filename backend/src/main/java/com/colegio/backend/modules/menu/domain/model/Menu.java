package com.colegio.backend.modules.menu.domain.model;

import com.colegio.backend.modules.auth.domain.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Menu {

    private Long id;
    private String code;
    private String name;
    private String icon;
    private String route;
    private Integer menuOrder;
    private String category;
    private Menu parent;
    private Set<Menu> children;
    private Set<Role> roles;
}
