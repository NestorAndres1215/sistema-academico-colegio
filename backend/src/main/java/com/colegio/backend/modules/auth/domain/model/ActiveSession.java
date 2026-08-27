package com.colegio.backend.modules.auth.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class ActiveSession {

    private Long sessionId;
    private Long userId;
    private String username;
    private String email;
    private LocalDateTime loginAt;
}