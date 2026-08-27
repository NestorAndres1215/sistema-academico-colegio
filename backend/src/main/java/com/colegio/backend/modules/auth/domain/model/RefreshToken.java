package com.colegio.backend.modules.auth.domain.model;

import com.colegio.backend.modules.user.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RefreshToken {

    private Long id;
    private String token;
    private User user;
    private String status;
    private LocalDateTime expiresAt;
    private LocalDateTime createdAt;

}