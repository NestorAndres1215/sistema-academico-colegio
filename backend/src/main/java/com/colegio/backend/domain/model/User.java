package com.colegio.backend.domain.model;

import com.colegio.backend.domain.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String id;
    private String email;
    private String password;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<Role> roles;
}
