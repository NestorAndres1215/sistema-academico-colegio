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
public class Session {

    private Long id;
    private User user;
    private RefreshToken refreshToken;
    private String status;
    private String ipAddress;
    private String location;
    private String userAgent;
    private LocalDateTime loginAt;
    private LocalDateTime logoutAt;
    private LocalDateTime lastActivityAt;
}