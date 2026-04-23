package com.colegio.backend.application.service;

import com.colegio.backend.application.dto.admin.AdministratorRequest;
import com.colegio.backend.domain.enums.Status;
import com.colegio.backend.domain.exception.ConflictException;
import com.colegio.backend.domain.exception.ResourceNotFoundException;
import com.colegio.backend.domain.model.Administrator;
import com.colegio.backend.domain.model.User;
import com.colegio.backend.domain.port.repository.AdministratorRepositoryPort;
import com.colegio.backend.domain.port.usecases.AdministratorUseCase;
import com.colegio.backend.domain.port.usecases.FileUseCase;
import com.colegio.backend.domain.port.usecases.UserUseCase;
import com.colegio.backend.infrastructure.util.SequenceGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdministratorService implements AdministratorUseCase {

    private final AdministratorRepositoryPort repositoryPort;
    private final UserUseCase userUseCase;
    private final FileUseCase fileUseCase;

    @Override
    public Administrator findById(String id) {
        return repositoryPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id not found"));
    }

    @Override
    public List<Administrator> findAll() {
        return repositoryPort.findAll();
    }

    @Override
    public Administrator save(MultipartFile file, AdministratorRequest request) {

        if (repositoryPort.existsByDni(request.getDni())) {
            throw new ConflictException("The identity document is already registered.");
        }

        if (request.getPhone() != null && repositoryPort.existsByPhone(request.getPhone())) {
            throw new ConflictException("The phone number is already registered.");
        }

        String fileName = fileUseCase.storeFile(file, "admin");

        String fileUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/assets/")
                .path(fileName)
                .toUriString();
        String newCode = SequenceGenerator.generateCode(repositoryPort.findLastCode());

        User user = userUseCase.save("", request.getEmail(), request.getPassword(), "ROLE_ADMINISTRATOR");
        LocalDate today = LocalDate.now();
        LocalDate birthDate = request.getBirthDate();

        int age = Period.between(birthDate, today).getYears();
        Administrator administrator = Administrator.builder()
                .id(newCode)
                .firstName(request.getFirstName())
                .middleName(request.getMiddleName())
                .paternalLastName(request.getPaternalLastName())
                .maternalLastName(request.getMaternalLastName())
                .dni(request.getDni())
                .phone(request.getPhone())
                .birthDate(request.getBirthDate())
                .profile(fileUrl)
                .age(age)
                .gender(request.getGender())
                .nationality(request.getNationality())
                .status(Status.ACTIVE)
                .user(user)
                .build();

        return repositoryPort.save(administrator);
    }

    @Override
    public Administrator update(String id, AdministratorRequest request) {

        Administrator existing = repositoryPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Administrator not found"));

        if (!existing.getDni().equals(request.getDni()) &&
                repositoryPort.existsByDni(request.getDni())) {
            throw new ConflictException("The identity document is already registered.");
        }

        if (request.getPhone() != null &&
                !request.getPhone().equals(existing.getPhone()) &&
                repositoryPort.existsByPhone(request.getPhone())) {
            throw new ConflictException("The phone number is already registered.");
        }

        existing.setFirstName(request.getFirstName());
        existing.setMiddleName(request.getMiddleName());
        existing.setPaternalLastName(request.getPaternalLastName());
        existing.setMaternalLastName(request.getMaternalLastName());
        existing.setDni(request.getDni());
        existing.setPhone(request.getPhone());
        existing.setBirthDate(request.getBirthDate());
        existing.setProfile(request.getProfile());
        existing.setGender(request.getGender());
        existing.setNationality(request.getNationality());

        return repositoryPort.save(existing);
    }

    @Override
    public Page<Administrator> getByStatus(Status status, String search, Pageable pageable) {
        return repositoryPort.getByStatus(status, search, pageable);
    }

    @Override
    public Administrator deactivate(String id) {
        Administrator existing = repositoryPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Administrator not found"));
        userUseCase.deactivateUser(existing.getUser().getId());
        existing.setStatus(Status.INACTIVE);
        return repositoryPort.save(existing);
    }

    @Override
    public Administrator activate(String id) {
        Administrator existing = repositoryPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Administrator not found"));
        userUseCase.activateUser(existing.getUser().getId());
        existing.setStatus(Status.ACTIVE);
        return repositoryPort.save(existing);
    }

}
