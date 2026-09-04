package com.colegio.backend.modules.teacher.application.service;

import com.colegio.backend.modules.file.domain.port.usecase.FileUseCase;
import com.colegio.backend.modules.teacher.domain.model.Teacher;
import com.colegio.backend.modules.teacher.domain.model.TeacherDetails;
import com.colegio.backend.modules.teacher.domain.port.repository.TeacherDetailsRepositoryPort;
import com.colegio.backend.modules.teacher.domain.port.usecase.TeacherDetailsUseCase;
import com.colegio.backend.shared.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class TeacherDetailsService implements TeacherDetailsUseCase {

    private final TeacherDetailsRepositoryPort teacherDetailsRepositoryPort;
    private final FileUseCase fileUseCase;

    @Override
    public TeacherDetails create(TeacherDetails teacherDetails, Teacher teacher, MultipartFile cv) {

        teacherDetails.setTeacher(teacher);

        saveCv(teacherDetails, cv);

        return teacherDetailsRepositoryPort.save(teacherDetails);
    }



    private void saveCv(TeacherDetails teacherDetails, MultipartFile cv) {

        if (cv == null || cv.isEmpty()) {
            return;
        }

        String fileUrl = fileUseCase.storeFile(cv, "teacher-cv");

        teacherDetails.setCurriculum(fileUrl);
    }


}
