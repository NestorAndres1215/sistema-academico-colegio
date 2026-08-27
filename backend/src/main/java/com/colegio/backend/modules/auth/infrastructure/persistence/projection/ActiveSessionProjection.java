package com.colegio.backend.modules.auth.infrastructure.persistence.projection;

import java.time.LocalDateTime;

public interface ActiveSessionProjection {

    Long getSessionId();

    Long getUserId();

    String getUsername();

    String getEmail();

    LocalDateTime getLoginAt();
}