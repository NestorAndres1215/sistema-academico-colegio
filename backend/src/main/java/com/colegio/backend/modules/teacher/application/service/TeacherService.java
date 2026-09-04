package com.colegio.backend.modules.teacher.application.service;

import com.colegio.backend.modules.file.domain.port.usecase.FileUseCase;
import com.colegio.backend.modules.teacher.application.dto.CreateTeacherRequest;
import com.colegio.backend.modules.teacher.application.dto.TeacherRequest;
import com.colegio.backend.modules.teacher.application.mapper.TeacherContractMapper;
import com.colegio.backend.modules.teacher.application.mapper.TeacherDetailsMapper;
import com.colegio.backend.modules.teacher.application.mapper.TeacherMapper;
import com.colegio.backend.modules.teacher.application.validator.TeacherValidator;
import com.colegio.backend.modules.teacher.domain.model.Teacher;
import com.colegio.backend.modules.teacher.domain.model.TeacherContract;
import com.colegio.backend.modules.teacher.domain.model.TeacherDetails;
import com.colegio.backend.modules.teacher.domain.port.repository.TeacherRepositoryPort;
import com.colegio.backend.modules.teacher.domain.port.usecase.TeacherContractUseCase;
import com.colegio.backend.modules.teacher.domain.port.usecase.TeacherDetailsUseCase;
import com.colegio.backend.modules.teacher.domain.port.usecase.TeacherUseCase;
import com.colegio.backend.modules.user.domain.model.User;
import com.colegio.backend.modules.user.domain.port.usecase.UserUseCase;
import com.colegio.backend.shared.constant.RoleConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class TeacherService implements TeacherUseCase {

    private final TeacherMapper teacherMapper;
    private final TeacherDetailsMapper teacherDetailsMapper;
    private final TeacherContractMapper teacherContractMapper;
    private final TeacherRepositoryPort teacherRepositoryPort;
    private final UserUseCase userUseCase;
    private final TeacherDetailsUseCase teacherDetailsUseCase;
    private final TeacherContractUseCase teacherContractUseCase;
    private final FileUseCase fileUseCase;
    private final TeacherValidator teacherValidator;

    @Override
    public Teacher create(TeacherRequest teacherRequest, MultipartFile foto, MultipartFile cv) {

        teacherValidator.validateBirthDate(
                teacherRequest.birthDate(),
                teacherRequest.startDate(),
                teacherRequest.endDate()
        );

        CreateTeacherRequest createTeacherRequest = teacherMapper.toCreateRequest(teacherRequest);

        Teacher teacher = createTeacher(createTeacherRequest, teacherRequest.email(), foto);

        TeacherDetails teacherDetails = teacherDetailsMapper.toDomain(teacherRequest,teacher);

        teacherDetailsUseCase.create(teacherDetails,teacher, cv);

        TeacherContract teacherContract = teacherContractMapper.toDomain(teacherRequest,teacher);

        teacherContractUseCase.create(teacherContract,teacher);

        return teacher;
    }

    private  Teacher createTeacher(CreateTeacherRequest createTeacherRequest,String email, MultipartFile foto){

        String code = generateCode(createTeacherRequest.dni());

        User user = userUseCase.save(email,code, createTeacherRequest.dni(), RoleConstants.TEACHER);

        teacherValidator.validate(createTeacherRequest,code);

        Teacher teacher = teacherMapper.toDomain(createTeacherRequest,user,code);

        savePhoto(teacher, foto);

        return teacherRepositoryPort.save(teacher);
    }

    private void savePhoto(Teacher teacher, MultipartFile foto) {

        if (foto == null || foto.isEmpty()) {
            return;
        }

        String fileUrl = fileUseCase.storeFile(foto, "teacher");

        teacher.setPhoto(fileUrl);
    }

    private String generateCode(String dni) {
        return "PR-" + dni;
    }

}
