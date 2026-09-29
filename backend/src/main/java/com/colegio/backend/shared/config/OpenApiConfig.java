package com.colegio.backend.shared.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class OpenApiConfig {

    private static final String SECURITY_SCHEME_NAME = "bearerAuth";

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Sistema de Gestión Escolar - API")
                        .version("1.0.0")
                        .description("""
                                API REST para la gestión integral de instituciones educativas.

                                Este sistema proporciona servicios centralizados para la gestión
                                académica, administrativa y operativa de una institución educativa.

                                La API permite gestionar usuarios, roles y permisos, estudiantes,
                                apoderados, docentes, personal administrativo, cursos, información
                                académica, asistencia, contratos, horarios, información institucional
                                y otros procesos necesarios para el funcionamiento diario del colegio.

                                El sistema está diseñado como una plataforma segura, modular y
                                escalable, capaz de adaptarse a las necesidades de diferentes
                                instituciones educativas.
                                """)
                        .contact(new Contact()
                                .name("Equipo de Desarrollo")
                                .email("support@school-system.com")
                        )
                        .license(new License()
                                .name("Propietaria")
                        )
                )
                .components(new Components()
                        .addSecuritySchemes(
                                SECURITY_SCHEME_NAME,
                                new SecurityScheme()
                                        .name(SECURITY_SCHEME_NAME)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                        .description("Ingrese el token de acceso JWT.")
                        )
                )
                .addSecurityItem(
                        new SecurityRequirement()
                                .addList(SECURITY_SCHEME_NAME)
                );
    }
}