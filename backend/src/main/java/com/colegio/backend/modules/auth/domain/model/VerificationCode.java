package com.colegio.backend.modules.auth.domain.model;

import com.colegio.backend.modules.user.domain.model.User;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VerificationCode {
    private Long id;
    private User user;
    private String verificationType;
    private String verificationCode;
    private LocalDateTime generatedAt;
    private LocalDateTime expiresAt;
    private Boolean verified;
    private Integer attempts;
}