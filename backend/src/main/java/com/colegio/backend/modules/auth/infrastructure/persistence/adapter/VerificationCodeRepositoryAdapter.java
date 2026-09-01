package com.colegio.backend.modules.auth.infrastructure.persistence.adapter;

import com.colegio.backend.modules.auth.domain.model.VerificationCode;
import com.colegio.backend.modules.auth.domain.port.repository.VerificationCodeRepositoryPort;
import com.colegio.backend.modules.auth.infrastructure.persistence.entity.VerificationCodeEntity;
import com.colegio.backend.modules.auth.infrastructure.persistence.mapper.VerificationCodeMapperPersistence;
import com.colegio.backend.modules.auth.infrastructure.persistence.repository.JpaVerificationCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class VerificationCodeRepositoryAdapter implements VerificationCodeRepositoryPort {

    private final JpaVerificationCodeRepository jpaVerificationCodeRepository;
    private final VerificationCodeMapperPersistence verificationCodeMapperPersistence;

    @Override
    public Optional<VerificationCode> findByUserEmail(String correo) {
        return jpaVerificationCodeRepository.findByUser_Email(correo)
                .map(verificationCodeMapperPersistence::toDomain);
    }

    @Override
    public VerificationCode create(VerificationCode verificationCode) {

        VerificationCodeEntity entity = verificationCodeMapperPersistence.toEntity(verificationCode);

        VerificationCodeEntity saved = jpaVerificationCodeRepository.save(entity);

        return verificationCodeMapperPersistence.toDomain(saved);
    }

    @Override
    public Optional<VerificationCode> findByVerificationCode(String verificationCode) {
        System.out.println("Código buscado en BD: " + verificationCode);
        return jpaVerificationCodeRepository.findByVerificationCode(verificationCode)
                .map(verificationCodeMapperPersistence::toDomain);
    }

    @Override
    public Optional<VerificationCode> findById(Long id) {
        return jpaVerificationCodeRepository.findById(id)
                .map(verificationCodeMapperPersistence::toDomain);
    }

}
