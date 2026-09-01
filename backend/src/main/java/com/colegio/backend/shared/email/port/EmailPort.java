package com.colegio.backend.shared.email.port;

import com.colegio.backend.modules.auth.domain.model.VerificationCode;
import jakarta.mail.MessagingException;

public interface EmailPort {

    void sendVerificationCode(VerificationCode verificationCode) throws MessagingException;
}