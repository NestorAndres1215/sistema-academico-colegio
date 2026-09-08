package com.colegio.backend.shared.utils;

import com.colegio.backend.shared.exception.BadRequestException;

import java.time.LocalDate;
import java.util.Set;
public final class ValidationUtils {

    private static final Set<String> MARITAL_STATUS = Set.of(
            "Soltero", "Casado", "Divorciado", "Viudo"
    );

    private static final Set<String> ACADEMIC_DEGREES = Set.of(
            "Bachiller", "Licenciado", "Magíster", "Doctor"
    );

    private static final Set<String> GENDERS = Set.of(
            "Masculino", "Femenino"
    );

    private ValidationUtils() {
    }

    public static void validateAge(LocalDate birthDate) {
        if (birthDate != null && birthDate.plusYears(18).isAfter(LocalDate.now())) {
            throw new BadRequestException("El profesor debe tener al menos 18 años");
        }
    }

    public static void validateMaritalStatus(String maritalStatus) {
        if (!MARITAL_STATUS.contains(maritalStatus)) {
            throw new BadRequestException("El estado civil no es válido");
        }
    }

    public static void validateAcademicDegree(String academicDegree) {
        if (!ACADEMIC_DEGREES.contains(academicDegree)) {
            throw new BadRequestException("El grado académico no es válido");
        }
    }

    public static void validateGender(String gender) {
        if (!GENDERS.contains(gender)) {
            throw new BadRequestException("El género debe ser Masculino o Femenino");
        }
    }
}