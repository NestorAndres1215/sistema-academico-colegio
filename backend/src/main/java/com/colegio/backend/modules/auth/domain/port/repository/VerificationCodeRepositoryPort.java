package com.colegio.backend.modules.auth.domain.port.repository;

import com.colegio.backend.modules.auth.domain.model.VerificationCode;
import java.util.Optional;

public interface VerificationCodeRepositoryPort {

    Optional<VerificationCode> findByUserEmail(String correo);

    VerificationCode create(VerificationCode verificationCode);

    Optional<VerificationCode> findByVerificationCode(String verificationCode);

    Optional<VerificationCode> findById(Long id);
}
