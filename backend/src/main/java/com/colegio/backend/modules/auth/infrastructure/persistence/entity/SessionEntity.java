package com.colegio.backend.modules.auth.infrastructure.persistence.entity;


import com.colegio.backend.modules.user.infrastructure.persistence.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "session")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SessionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "refresh_token_id")
    private RefreshTokenEntity refreshToken;

    private String status;

    @Column(name = "ip_address", length = 45)
    private String ipAddress;

    @Column(name = "location", length = 150)
    private String location;

    @Column(name = "user_agent", length = 500)
    private String userAgent;

    @Column(name = "login_at")
    private LocalDateTime loginAt;

    @Column(name = "logout_at")
    private LocalDateTime logoutAt;

    @Column(name = "last_activity_at")
    private LocalDateTime lastActivityAt;
}