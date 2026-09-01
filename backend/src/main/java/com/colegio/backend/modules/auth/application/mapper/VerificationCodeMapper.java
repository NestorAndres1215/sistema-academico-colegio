package com.colegio.backend.modules.auth.application.mapper;

import com.colegio.backend.modules.auth.application.dto.VerificationCodeResponse;
import com.colegio.backend.modules.auth.domain.model.VerificationCode;
import com.colegio.backend.modules.user.domain.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class VerificationCodeMapper {

    public VerificationCodeResponse toResponse(VerificationCode verificationCode) {
        return new VerificationCodeResponse(
                verificationCode.getId(),
                verificationCode.getVerificationType(),
                verificationCode.getVerificationCode(),
                verificationCode.getGeneratedAt(),
                verificationCode.getExpiresAt()
        );
    }

    public VerificationCode createPasswordRecoveryCode(User user, String verificationCode, LocalDateTime generatedAt) {
        return VerificationCode.builder()
                .user(user)
                .verificationType("PASSWORD_RECOVERY")
                .verificationCode(verificationCode)
                .generatedAt(generatedAt)
                .expiresAt(generatedAt.plusMinutes(10))
                .verified(false)
                .attempts(0)
                .build();
    }

    public VerificationCode updateCode(VerificationCode existing, String verificationCode, LocalDateTime generatedAt) {
        existing.setVerificationCode(verificationCode);
        existing.setGeneratedAt(generatedAt);
        existing.setExpiresAt(generatedAt.plusMinutes(10));
        existing.setVerified(false);
        existing.setAttempts(0);

        return existing;
    }
}