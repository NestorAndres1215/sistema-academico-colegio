package com.colegio.backend.modules.teacher.application.validator;

import com.colegio.backend.modules.teacher.application.dto.teacher.CreateTeacherRequest;
import com.colegio.backend.modules.teacher.application.dto.teacher.UpdateTeacherRequest;
import com.colegio.backend.modules.teacher.domain.model.Teacher;
import com.colegio.backend.modules.teacher.domain.port.repository.TeacherRepositoryPort;
import com.colegio.backend.shared.exception.BadRequestException;
import com.colegio.backend.shared.exception.ConflictException;
import com.colegio.backend.shared.utils.ValidationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;


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

        ValidationUtils.validateGender(request.gender());
        ValidationUtils.validateAge(request.birthDate());
        ValidationUtils.validateMaritalStatus(request.maritalStatus());
        ValidationUtils.validateAcademicDegree(request.academicDegree());

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




    public void validateUpdate(
            UpdateTeacherRequest request,
            Teacher teacher
    ) {
        ValidationUtils.validateGender(request.gender());
        ValidationUtils.validateAge(request.birthDate());
        ValidationUtils.validateMaritalStatus(request.maritalStatus());
        ValidationUtils.validateAcademicDegree(request.academicDegree());

        if (!teacher.getDni().equals(request.dni())
                && teacherRepositoryPort.existsByDni(request.dni())) {

            throw new ConflictException("El DNI ya está registrado");
        }

        if (!teacher.getPhone().equals(request.phone())
                && teacherRepositoryPort.existsByPhone(request.phone())) {

            throw new ConflictException("El teléfono ya está registrado");
        }

        if (request.professionalLicenseNumber() != null
                && !request.professionalLicenseNumber().isBlank()
                && !request.professionalLicenseNumber().equals(
                teacher.getProfessionalLicenseNumber())
                && teacherRepositoryPort.existsByProfessionalLicenseNumber(
                request.professionalLicenseNumber())) {

            throw new ConflictException(
                    "El número de colegiatura ya está registrado"
            );
        }
    }
}