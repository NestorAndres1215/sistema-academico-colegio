package com.colegio.backend.modules.teacher.application.validator;

import com.colegio.backend.modules.teacher.application.dto.CreateTeacherRequest;
import com.colegio.backend.modules.teacher.domain.port.repository.TeacherRepositoryPort;
import com.colegio.backend.shared.exception.BadRequestException;
import com.colegio.backend.shared.exception.ConflictException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class TeacherValidator {

    private final TeacherRepositoryPort teacherRepositoryPort;

    public void validateBirthDate(LocalDate birthDate, LocalDate startDate, LocalDate endDate) {

        LocalDate minimumBirthDate = LocalDate.now().minusYears(18);

        if (birthDate.isAfter(minimumBirthDate)) {
            throw new BadRequestException("El profesor debe tener al menos 18 años");
        }

        if (!birthDate.isBefore(startDate)) {
            throw new BadRequestException("La fecha de nacimiento debe ser anterior a la fecha de inicio");
        }

        if (endDate != null && !birthDate.isBefore(endDate)) {
            throw new BadRequestException("La fecha de nacimiento debe ser anterior a la fecha de finalización");
        }
    }


    public void validate(CreateTeacherRequest request, String code) {

        validateGender(request.gender());
        validateAge(request.birthDate());
        validateMaritalStatus(request.maritalStatus());
        validateAcademicDegree(request.academicDegree());

        if (teacherRepositoryPort.existsByDni(request.dni())) {
            throw new ConflictException("El DNI ya está registrado");
        }

        if (request.professionalLicenseNumber() != null
                && !request.professionalLicenseNumber().isBlank()
                && teacherRepositoryPort.existsByProfessionalLicenseNumber(
                request.professionalLicenseNumber())) {

            throw new ConflictException("El número de colegiatura ya está registrado");
        }

        if (teacherRepositoryPort.existsByPhone(request.phone())) {
            throw new ConflictException("El teléfono ya está registrado");
        }

        if (teacherRepositoryPort.existsByCode(code)) {
            throw new ConflictException("El código del profesor ya está registrado");
        }
    }

    private void validateGender(String gender) {
        if (!"Masculino".equals(gender) && !"Femenino".equals(gender)) {
            throw new BadRequestException("El género debe ser Masculino o Femenino");
        }
    }

    private void validateAge(LocalDate birthDate) {
        if (birthDate == null) {
            return;
        }

        if (birthDate.plusYears(18).isAfter(LocalDate.now())) {
            throw new BadRequestException("El profesor debe tener al menos 18 años");
        }
    }

    private void validateMaritalStatus(String maritalStatus) {
        if (!Set.of("Soltero", "Casado", "Divorciado", "Viudo").contains(maritalStatus)) {
            throw new BadRequestException("El estado civil no es válido");
        }
    }

    private void validateAcademicDegree(String academicDegree) {
        if (!Set.of("Bachiller", "Licenciado", "Magíster", "Doctor").contains(academicDegree)) {
            throw new BadRequestException("El grado académico no es válido");
        }
    }
}