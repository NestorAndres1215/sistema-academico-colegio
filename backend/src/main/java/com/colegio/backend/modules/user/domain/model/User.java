package com.colegio.backend.modules.user.domain.model;

import com.colegio.backend.modules.auth.domain.model.Role;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class User {

    private Long id;
    private String username;
    private String email;
    private String password;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<Role> roles;
}