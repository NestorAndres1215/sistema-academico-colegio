package com.colegio.backend.application.service;

import com.colegio.backend.application.dto.teacher.TeacherRequest;
import com.colegio.backend.domain.enums.Status;
import com.colegio.backend.domain.exception.ConflictException;
import com.colegio.backend.domain.exception.ResourceNotFoundException;
import com.colegio.backend.domain.model.Teacher;
import com.colegio.backend.domain.model.User;
import com.colegio.backend.domain.port.repository.TeacherRepositoryPort;
import com.colegio.backend.domain.port.usecases.FileUseCase;
import com.colegio.backend.domain.port.usecases.TeacherUseCase;
import com.colegio.backend.domain.port.usecases.UserUseCase;
import com.colegio.backend.infrastructure.util.SequenceGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService implements TeacherUseCase {

    private final TeacherRepositoryPort repositoryPort;
    private final UserUseCase userUseCase;
    private final FileUseCase fileUseCase;

    @Override
    public Teacher findById(String id) {
        return repositoryPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id not found"));
    }

    @Override
    public List<Teacher> findAll() {
        return repositoryPort.findAll();
    }

    @Override
    public Teacher save(MultipartFile file, TeacherRequest teacherRequest) {

        if (repositoryPort.existsByDni(teacherRequest.getDni())) {
            throw new ConflictException("The identity document is already registered.");
        }

        if (teacherRequest.getPhone() != null && repositoryPort.existsByPhone(teacherRequest.getPhone())) {
            throw new ConflictException("The phone number is already registered.");
        }

        String fileName = fileUseCase.storeFile(file,"teacher");

        String fileUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/assets/")
                .path(fileName)
                .toUriString();

        String newCode = SequenceGenerator.generateCode(repositoryPort.findLastCode());

        User user = userUseCase.save("",teacherRequest.getEmail(),teacherRequest.getPassword(), "ROLE_TEACHER");
        LocalDate today = LocalDate.now();
        LocalDate birthDate = teacherRequest.getBirthDate();
        int age = Period.between(birthDate, today).getYears();

        Teacher teacher = Teacher.builder()
                .id(newCode)
                .firstName(teacherRequest.getFirstName())
                .middleName(teacherRequest.getMiddleName())
                .paternalLastName(teacherRequest.getPaternalLastName())
                .maternalLastName(teacherRequest.getMaternalLastName())
                .age(age)
                .dni(teacherRequest.getDni())
                .phone(teacherRequest.getPhone())
                .birthDate(birthDate)
                .profile(fileUrl)
                .gender(teacherRequest.getGender())
                .nationality(teacherRequest.getNationality())
                .status(Status.ACTIVE)
                .hireDate(teacherRequest.getHireDate())
                .createdAt(LocalDateTime.now())
                .user(user)
                .build();
        return repositoryPort.save(teacher);
    }

    @Override
    public Teacher update(String id, MultipartFile file, TeacherRequest teacherRequest) {

        Teacher existing = repositoryPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found"));

        if (!existing.getDni().equals(teacherRequest.getDni()) &&
                repositoryPort.existsByDni(teacherRequest.getDni())) {
            throw new ConflictException("The identity document is already registered.");
        }


        if (teacherRequest.getPhone() != null &&
                !teacherRequest.getPhone().equals(existing.getPhone()) &&
                repositoryPort.existsByPhone(teacherRequest.getPhone())) {
            throw new ConflictException("The phone number is already registered.");
        }

        if (file != null && !file.isEmpty()) {
            String fileName = fileUseCase.storeFile(file, "teacher");

            String fileUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/assets/")
                    .path(fileName)
                    .toUriString();

            existing.setProfile(fileUrl);
        }

        if (teacherRequest.getBirthDate() != null &&
                !teacherRequest.getBirthDate().equals(existing.getBirthDate())) {

            LocalDate today = LocalDate.now();
            int age = Period.between(teacherRequest.getBirthDate(), today).getYears();

            existing.setBirthDate(teacherRequest.getBirthDate());
            existing.setAge(age);
        }

        existing.setFirstName(teacherRequest.getFirstName());
        existing.setMiddleName(teacherRequest.getMiddleName());
        existing.setPaternalLastName(teacherRequest.getPaternalLastName());
        existing.setMaternalLastName(teacherRequest.getMaternalLastName());
        existing.setDni(teacherRequest.getDni());
        existing.setPhone(teacherRequest.getPhone());
        existing.setGender(teacherRequest.getGender());
        existing.setNationality(teacherRequest.getNationality());
        existing.setHireDate(teacherRequest.getHireDate());

        existing.setUpdatedAt(LocalDateTime.now());

        return repositoryPort.save(existing);
    }
    @Override
    public Page<Teacher> getByStatus(Status status, String search, Pageable pageable) {
        return repositoryPort.getByStatus(status, search, pageable);
    }

    @Override
    public Teacher deactivate(String id) {
        Teacher existing = repositoryPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found"));

        userUseCase.deactivateUser(existing.getUser().getId());

        existing.setStatus(Status.INACTIVE);

        return repositoryPort.save(existing);
    }

    @Override
    public Teacher activate(String id) {
        Teacher existing = repositoryPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found"));

        userUseCase.activateUser(existing.getUser().getId());

        existing.setStatus(Status.ACTIVE);

        return repositoryPort.save(existing);
    }
}
