package com.colegio.backend.shared.email.adapter;

import com.colegio.backend.modules.auth.domain.model.VerificationCode;
import com.colegio.backend.shared.email.port.EmailPort;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailAdapter implements EmailPort {

    private final JavaMailSender javaMailSender;

    @Override
    public void sendVerificationCode(VerificationCode verificationCode) throws MessagingException {
        final String recipient = verificationCode.getUser().getEmail();
        final String username = verificationCode.getUser().getUsername();
        final String code = verificationCode.getVerificationCode();
        final String subject = "Código de verificación en dos pasos - Colegio San Andrés";

        String contentHtml = """
                <html>
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                </head>
                <body style="margin:0; padding:0; background-color:#F4F6FA; font-family:'Segoe UI', Arial, sans-serif;">
                    <table role="presentation" width="100%%" cellpadding="0" cellspacing="0" style="background-color:#F4F6FA; padding:32px 12px;">
                        <tr>
                            <td align="center">
                                <table role="presentation" width="100%%" cellpadding="0" cellspacing="0" style="max-width:520px; background-color:#FFFFFF; border-radius:14px; overflow:hidden; box-shadow:0 4px 18px rgba(26, 44, 91, 0.15); border:1px solid #D0D7E8;">

                                    <!-- Header / Banner -->
                                    <tr>
                                        <td style="background-color:#1A3A6B; padding:28px 24px; text-align:center;">
                                            <div style="font-family:Georgia, 'Times New Roman', serif; color:#FFFFFF; font-size:22px; font-weight:bold; letter-spacing:0.5px;">
                                                COLEGIO SAN ANDRÉS
                                            </div>
                                            <div style="margin-top:10px; display:inline-block; background-color:#F5A623; color:#1A3A6B; font-weight:bold; font-size:13px; text-transform:uppercase; letter-spacing:1px; padding:6px 16px; border-radius:20px;">
                                                Verificación en dos pasos
                                            </div>
                                        </td>
                                    </tr>

                                    <!-- Body -->
                                    <tr>
                                        <td style="padding:32px 30px;">
                                            <p style="margin:0 0 6px 0; color:#1A3A6B; font-size:20px; font-weight:bold;">
                                                Hola, %s
                                            </p>
                                            <p style="margin:0 0 22px 0; color:#5A6D8C; font-size:15px; line-height:1.6;">
                                                Hemos recibido una solicitud para <strong style="color:#1A3A6B;">restablecer tu contraseña</strong> en la plataforma del Colegio San Andrés. Utiliza el siguiente código para continuar con el proceso:
                                            </p>

                                            <!-- Code box -->
                                            <table role="presentation" width="100%%" cellpadding="0" cellspacing="0" style="margin:0 0 22px 0;">
                                                <tr>
                                                    <td align="center" style="background-color:#F4F6FA; border:1px dashed #1A3A6B; border-radius:10px; padding:18px;">
                                                        <span style="font-size:30px; font-weight:bold; letter-spacing:6px; color:#1A3A6B; font-family:'Segoe UI', Arial, sans-serif;">
                                                            %s
                                                        </span>
                                                    </td>
                                                </tr>
                                            </table>

                                            <p style="margin:0 0 18px 0; color:#5A6D8C; font-size:14px; line-height:1.6;">
                                                Este código es válido por <strong style="color:#1A3A6B;">10 minutos</strong>. Ingrésalo en la pantalla de verificación para completar el proceso de recuperación.
                                            </p>

                                            <!-- Warning box -->
                                            <table role="presentation" width="100%%" cellpadding="0" cellspacing="0" style="margin:0 0 18px 0;">
                                                <tr>
                                                    <td style="background-color:#FDECEC; border-left:4px solid #D32F2F; border-radius:6px; padding:14px 16px;">
                                                        <span style="color:#D32F2F; font-weight:bold; font-size:13px;">IMPORTANTE:</span>
                                                        <span style="color:#5A6D8C; font-size:13px; line-height:1.5;">
                                                            No compartas este código con nadie. El equipo del Colegio San Andrés <strong>nunca</strong> te lo pedirá por correo, llamadas ni mensajes.
                                                        </span>
                                                    </td>
                                                </tr>
                                            </table>

                                            <p style="margin:0; color:#5A6D8C; font-size:13px; line-height:1.6;">
                                                Si tú no solicitaste este cambio, simplemente ignora este mensaje. Tu cuenta seguirá protegida.
                                            </p>
                                        </td>
                                    </tr>

                                    <!-- Footer -->
                                    <tr>
                                        <td style="background-color:#F4F6FA; padding:18px 24px; text-align:center; border-top:1px solid #D0D7E8;">
                                            <p style="margin:0; color:#5A6D8C; font-size:12px; line-height:1.5;">
                                                Este es un mensaje automático. Por favor, no respondas a este correo.
                                            </p>
                                            <p style="margin:6px 0 0 0; color:#1A3A6B; font-size:12px; font-weight:bold;">
                                                Colegio San Andrés
                                            </p>
                                        </td>
                                    </tr>

                                </table>
                            </td>
                        </tr>
                    </table>
                </body>
                </html>
                """.formatted(username, code);

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(recipient);
        helper.setSubject(subject);
        helper.setText(contentHtml, true);
        javaMailSender.send(message);
    }
}