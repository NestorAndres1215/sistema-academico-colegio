package com.colegio.backend.modules.teacher.application.mapper;

import com.colegio.backend.modules.teacher.application.dto.teacher.TeacherListResponse;
import com.colegio.backend.modules.teacher.application.dto.teacher.TeacherRequest;
import com.colegio.backend.modules.teacher.domain.model.Teacher;
import com.colegio.backend.modules.teacher.domain.model.TeacherDetails;
import com.colegio.backend.modules.user.domain.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class TeacherDetailsMapper {

    public TeacherDetails toDomain(TeacherRequest request, Teacher teacher) {
        return TeacherDetails.builder()
                .teacher(teacher)
                .university(request.university())
                .graduationDate(request.graduationDate())
                .yearsOfExperience(request.yearsOfExperience())
                .curriculum("")
                .notes(request.notes())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public TeacherListResponse toListResponse(
            Teacher teacher,
            TeacherDetails details
    ) {

        User user = teacher.getUser();

        return new TeacherListResponse(
                teacher.getId(),
                teacher.getCode(),
                user.getEmail(),
                user.getUsername(),
                buildName(teacher.getFirstName(), teacher.getMiddleName()),
                buildName(teacher.getPaternalLastName(), teacher.getMaternalLastName()),
                teacher.getFirstName(),
                teacher.getMiddleName(),
                teacher.getPaternalLastName(),
                teacher.getMaternalLastName(),
                teacher.getDni(),
                teacher.getBirthDate().toString(),
                teacher.getGender(),
                teacher.getMaritalStatus(),
                teacher.getPhone(),
                teacher.getAddress(),
                teacher.getSpecialty(),
                teacher.getAcademicDegree(),
                teacher.getProfessionalLicenseNumber(),
                details.getUniversity(),
                details.getGraduationDate().toString(),
                 details.getYearsOfExperience(),
                details.getCurriculum(),
                details.getNotes(),
                teacher.getPhoto(),
                teacher.getStatus()
        );
    }

    private static String buildName(String... names) {
        return Arrays.stream(names)
                .filter(Objects::nonNull)
                .filter(name -> !name.isBlank())
                .collect(Collectors.joining(" "));
    }

}