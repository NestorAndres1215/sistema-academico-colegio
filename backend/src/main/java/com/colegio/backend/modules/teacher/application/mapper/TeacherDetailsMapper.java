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


    public  TeacherListResponse toListResponse(Teacher teacher, TeacherDetails details) {
        User user = teacher.getUser();

        return new TeacherListResponse(
                teacher.getId(),
                teacher.getCode(),
                valueOrEmpty(user, User::getEmail),
                valueOrEmpty(user, User::getUsername),
                buildName(teacher.getFirstName(), teacher.getMiddleName()),
                buildName(teacher.getPaternalLastName(), teacher.getMaternalLastName()),
                teacher.getDni(),
                teacher.getBirthDate() != null ? teacher.getBirthDate().toString() : "",
                teacher.getPhone(),
                teacher.getSpecialty(),
                teacher.getAcademicDegree(),
                teacher.getProfessionalLicenseNumber(),
                valueOrEmpty(details, TeacherDetails::getUniversity),
                details != null ? details.getYearsOfExperience() : 0,
                valueOrEmpty(details, TeacherDetails::getNotes)
        );
    }

    private static String buildName(String... names) {
        return Arrays.stream(names)
                .filter(Objects::nonNull)
                .filter(name -> !name.isBlank())
                .collect(Collectors.joining(" "));
    }

    private static String valueOrEmpty(User user, Function<User, String> getter) {
        return user != null && getter.apply(user) != null
                ? getter.apply(user)
                : "";
    }

    private static String valueOrEmpty(TeacherDetails details, Function<TeacherDetails, String> getter) {
        return details != null && getter.apply(details) != null
                ? getter.apply(details)
                : "";
    }
}