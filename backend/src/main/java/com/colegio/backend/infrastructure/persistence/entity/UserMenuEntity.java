package com.colegio.backend.infrastructure.persistence.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_menu")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserMenuEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private MenuEntity menu;

    private Boolean active;
}
