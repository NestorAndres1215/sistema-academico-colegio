package com.colegio.backend.modules.auth.application.service;

import com.colegio.backend.modules.auth.application.mapper.VerificationCodeMapper;
import com.colegio.backend.modules.auth.domain.model.VerificationCode;
import com.colegio.backend.modules.auth.domain.port.repository.VerificationCodeRepositoryPort;
import com.colegio.backend.modules.auth.domain.port.usecase.VerificationCodeUseCase;
import com.colegio.backend.modules.user.domain.model.User;
import com.colegio.backend.modules.user.domain.port.repository.UserRepositoryPort;
import com.colegio.backend.shared.email.port.EmailPort;
import com.colegio.backend.shared.exception.BadRequestException;
import com.colegio.backend.shared.exception.NotFoundException;
import com.colegio.backend.shared.utils.CodeGenerator;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class VerificationCodeService implements VerificationCodeUseCase {

    private final VerificationCodeRepositoryPort verificationCodeRepositoryPort;
    private final UserRepositoryPort userRepositoryPort;
    private final VerificationCodeMapper verificationCodeMapper;
    private final EmailPort emailPort;

    @Override
    public VerificationCode passwordRecoveryVerification(String email) {

        User user = findByEmail(email);

        LocalDateTime now = LocalDateTime.now();

        String code = CodeGenerator.generateCode();

        VerificationCode verificationCode = getOrCreateVerificationCode(user, email, code, now);

        sendVerificationCode(verificationCode);

        return create(verificationCode);
    }


    public VerificationCode create(VerificationCode verificationCode) {
        return verificationCodeRepositoryPort.create(verificationCode);
    }

    private VerificationCode getOrCreateVerificationCode(User user, String email, String code, LocalDateTime now) {
        return verificationCodeRepositoryPort.findByUserEmail(email)
                .map(existing ->
                        verificationCodeMapper.updateCode(existing, code, now)
                )
                .orElseGet(() ->
                        verificationCodeMapper.createPasswordRecoveryCode(user, code, now)
                );
    }

    private void sendVerificationCode(VerificationCode verificationCode) {
        try {
            emailPort.sendVerificationCode(verificationCode);
        } catch (MessagingException e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @Override
    public VerificationCode verificationCode(String code) {

        VerificationCode verificationCode = findByVerificationCode(code);

        verificationCode.setVerificationCode(null);
        verificationCode.setGeneratedAt(LocalDateTime.now());

        return create(verificationCode);
    }

    public VerificationCode findByVerificationCode(String code) {
        return verificationCodeRepositoryPort.findByVerificationCode(code)
                .orElseThrow(() ->
                        new NotFoundException("Código de verificación no encontrado"));
    }

    public User findByEmail(String email) {
        return userRepositoryPort.findByEmail(email)
                .orElseThrow(() ->
                        new NotFoundException("Correo no encontrado"));
    }

}
