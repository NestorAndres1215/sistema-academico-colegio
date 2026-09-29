package com.colegio.backend.modules.auth.infrastructure.controller;

import com.colegio.backend.modules.auth.application.dto.VerificationCodeResponse;
import com.colegio.backend.modules.auth.domain.model.VerificationCode;
import com.colegio.backend.modules.auth.domain.port.usecase.VerificationCodeUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/verification-code")
@Tag(name = "Verification Code")
public class VerificationCodeController {

    private  final VerificationCodeUseCase verificationCodeUseCase;

    @Operation(summary = "Enviar código de recuperación al correo")
    @PostMapping("/verify/email/{username}")
    public ResponseEntity<VerificationCode> verifyEmail(@PathVariable String username) {
        return ResponseEntity.ok(verificationCodeUseCase.passwordRecoveryVerification(username));
    }

    @Operation(summary = "Verificar código de recuperación")
    @PostMapping("/verify/code/{verificationCode}")
    public ResponseEntity<VerificationCode> verificationCode(@PathVariable String verificationCode) {
        return ResponseEntity.ok(verificationCodeUseCase.verificationCode(verificationCode));
    }
}
