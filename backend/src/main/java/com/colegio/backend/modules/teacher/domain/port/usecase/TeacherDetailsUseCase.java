package com.colegio.backend.modules.teacher.domain.port.usecase;

import com.colegio.backend.modules.teacher.domain.model.Teacher;
import com.colegio.backend.modules.teacher.domain.model.TeacherDetails;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;

public interface TeacherDetailsUseCase {

    TeacherDetails create(TeacherDetails teacherDetails, Teacher teacher, MultipartFile cv);

    Resource downloadCurriculum(Long teacherId) throws MalformedURLException;
}
