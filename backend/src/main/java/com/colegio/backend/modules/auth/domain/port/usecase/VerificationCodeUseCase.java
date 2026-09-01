package com.colegio.backend.modules.auth.domain.port.usecase;

import com.colegio.backend.modules.auth.domain.model.VerificationCode;

public interface VerificationCodeUseCase {

    VerificationCode passwordRecoveryVerification(String email);

    VerificationCode verificationCode(String verificationCode);
}
