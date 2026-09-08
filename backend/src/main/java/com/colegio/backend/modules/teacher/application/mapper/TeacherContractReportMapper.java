package com.colegio.backend.modules.teacher.application.mapper;

import com.colegio.backend.modules.teacher.application.dto.teacher_contract.TeacherContractReportResponse;
import com.colegio.backend.modules.teacher.domain.model.Teacher;
import com.colegio.backend.modules.teacher.domain.model.TeacherContract;
import org.springframework.stereotype.Component;

@Component
public class TeacherContractReportMapper {

    public  TeacherContractReportResponse toResponse(
            TeacherContract contract
    ) {

        Teacher teacher = contract.getTeacher();

        return new TeacherContractReportResponse(

                contract.getId(),
                contract.getContractType(),
                contract.getStartDate(),
                contract.getEndDate(),
                contract.getPosition(),
                contract.getWeeklyHours(),
                contract.getSalary(),
                contract.getStatus(),
                contract.getCreatedAt(),
                contract.getUpdatedAt(),

                teacher.getId(),
                teacher.getCode(),
                teacher.getFirstName(),
                teacher.getMiddleName(),
                teacher.getPaternalLastName(),
                teacher.getMaternalLastName(),
                teacher.getDni(),
                teacher.getBirthDate(),
                teacher.getGender(),
                teacher.getMaritalStatus(),
                teacher.getPhone(),
                teacher.getAddress(),
                teacher.getSpecialty(),
                teacher.getAcademicDegree(),
                teacher.getProfessionalLicenseNumber(),
                teacher.getPhoto(),
                teacher.getStatus(),
                teacher.getCreatedAt(),
                teacher.getUpdatedAt()
        );
    }

}
