package com.colegio.backend.modules.teacher.application.service;

import com.colegio.backend.modules.file.domain.port.usecase.FileUseCase;
import com.colegio.backend.modules.teacher.application.validator.TeacherContractValidator;
import com.colegio.backend.modules.teacher.domain.model.Teacher;
import com.colegio.backend.modules.teacher.domain.model.TeacherContract;
import com.colegio.backend.modules.teacher.domain.port.repository.TeacherContractRepositoryPort;
import com.colegio.backend.modules.teacher.domain.port.usecase.TeacherContractUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherContractService implements TeacherContractUseCase {

    private final TeacherContractRepositoryPort teacherContractRepositoryPort;
    private final TeacherContractValidator teacherContractValidator;

    @Override
    public TeacherContract create(TeacherContract teacherContract, Teacher teacher) {

        teacherContract.setTeacher(teacher);

        teacherContractValidator.validateContract(
                teacherContract.getContractType(),
                teacherContract.getStartDate(),
                teacherContract.getEndDate()
        );

        return teacherContractRepositoryPort.save(teacherContract);
    }

}
