package com.colegio.backend.modules.auth.infrastructure.persistence.repository;

import com.colegio.backend.modules.auth.infrastructure.persistence.entity.VerificationCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaVerificationCodeRepository extends JpaRepository<VerificationCodeEntity,Long> {

    Optional<VerificationCodeEntity> findByUser_Email(String email);

    Optional<VerificationCodeEntity> findByVerificationCode(String verificationCode);

}
