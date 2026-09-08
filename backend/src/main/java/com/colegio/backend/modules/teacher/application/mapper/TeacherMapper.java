package com.colegio.backend.modules.teacher.application.mapper;

import com.colegio.backend.modules.teacher.application.dto.teacher.CreateTeacherRequest;
import com.colegio.backend.modules.teacher.application.dto.teacher.TeacherRequest;
import com.colegio.backend.modules.teacher.application.dto.teacher.TeacherResponse;
import com.colegio.backend.modules.teacher.application.dto.teacher.UpdateTeacherRequest;
import com.colegio.backend.modules.teacher.domain.model.Teacher;
import com.colegio.backend.modules.user.domain.model.User;
import com.colegio.backend.shared.constant.StatusConstants;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class TeacherMapper {

    public CreateTeacherRequest toCreateRequest(TeacherRequest request) {
        return new CreateTeacherRequest(
                request.firstName(),
                request.middleName(),
                request.paternalLastName(),
                request.maternalLastName(),
                request.dni(),
                request.birthDate(),
                request.gender(),
                request.maritalStatus(),
                request.phone(),
                request.address(),
                request.specialty(),
                request.academicDegree(),
                request.professionalLicenseNumber()
        );
    }

    public void updateDomain(UpdateTeacherRequest request, Teacher teacher) {

        teacher.setFirstName(request.firstName());
        teacher.setMiddleName(request.middleName());
        teacher.setPaternalLastName(request.paternalLastName());
        teacher.setMaternalLastName(request.maternalLastName());
        teacher.setDni(request.dni());
        teacher.setBirthDate(request.birthDate());
        teacher.setGender(request.gender());
        teacher.setMaritalStatus(request.maritalStatus());
        teacher.setPhone(request.phone());
        teacher.setAddress(request.address());
        teacher.setSpecialty(request.specialty());
        teacher.setAcademicDegree(request.academicDegree());
        teacher.setProfessionalLicenseNumber(request.professionalLicenseNumber());
    }

    public Teacher toDomain(CreateTeacherRequest request, User user, String code) {
        return Teacher.builder()
                .code(code)
                .user(user)
                .firstName(request.firstName())
                .middleName(request.middleName())
                .paternalLastName(request.paternalLastName())
                .maternalLastName(request.maternalLastName())
                .dni(request.dni())
                .birthDate(request.birthDate())
                .gender(request.gender())
                .maritalStatus(request.maritalStatus())
                .phone(request.phone())
                .address(request.address())
                .specialty(request.specialty())
                .academicDegree(request.academicDegree())
                .professionalLicenseNumber(request.professionalLicenseNumber())
                .status(StatusConstants.ACTIVE)
                .createdAt(LocalDateTime.now())
                .build();
    }

    public TeacherResponse toResponse(Teacher teacher) {

        return new TeacherResponse(
                teacher.getId(),
                teacher.getCode(),
                join(teacher.getFirstName(), teacher.getMiddleName()),
                join(teacher.getPaternalLastName(), teacher.getMaternalLastName()),
                teacher.getDni(),
                teacher.getPhoto()
        );
    }

    private String join(String... values) {
        return Arrays.stream(values)
                .filter(Objects::nonNull)
                .filter(value -> !value.isBlank())
                .collect(Collectors.joining(" "));
    }
}
