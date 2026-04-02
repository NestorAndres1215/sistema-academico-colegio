package com.colegio.backend.application.service;

import com.colegio.backend.application.dto.company.CompanyRequest;
import com.colegio.backend.domain.exception.ResourceNotFoundException;
import com.colegio.backend.domain.model.Company;
import com.colegio.backend.domain.port.repository.CompanyRepositoryPort;
import com.colegio.backend.domain.port.usecases.CompanyUseCase;
import com.colegio.backend.domain.port.usecases.FileUseCase;
import com.colegio.backend.infrastructure.util.SequenceGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class CompanyService implements CompanyUseCase {

   private final CompanyRepositoryPort repositoryPort;
   private final FileUseCase fileUseCase;

    @Override
    public List<Company> findAll() {
        return repositoryPort.findAll();
    }

    @Override
    public Company findByName(String name) {
        return repositoryPort.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found"));
    }

    @Override
    public Company findById(String id) {
        return repositoryPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found"));
    }



    @Override
    public Company save(MultipartFile logo, CompanyRequest companyRequest) throws IOException {

        if (logo.isEmpty()) {
            throw new RuntimeException("El archivo está vacío");
        }
        String fileName = fileUseCase.storeFile(logo);

        String fileUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/colegio/api/v1/assets/company")
                .path(fileName)
                .toUriString();

        String newCode = SequenceGenerator.generateCode(repositoryPort.findLastCode());

        Company company = Company.builder()
                .id(newCode)
                .name(companyRequest.getName())
                .logo(fileUrl)
                .ruc(companyRequest.getRuc())
                .description(companyRequest.getDescription())
                .phone(companyRequest.getPhone())
                .email(companyRequest.getEmail())
                .address(companyRequest.getAddress())
                .city(companyRequest.getCity())
                .country(companyRequest.getCountry())
                .companyType(companyRequest.getCompanyType())
                .foundingDate(companyRequest.getFoundingDate())
                .createdAt(LocalDateTime.now())
                .build();

        return repositoryPort.save(company);
    }

    @Override
    public Company update(String id, CompanyRequest companyRequest) {

        Company existing = repositoryPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found"));

        existing.setName(companyRequest.getName());
        existing.setLogo(companyRequest.getLogo());
        existing.setRuc(companyRequest.getRuc());
        existing.setDescription(companyRequest.getDescription());
        existing.setPhone(companyRequest.getPhone());
        existing.setEmail(companyRequest.getEmail());
        existing.setAddress(companyRequest.getAddress());
        existing.setCity(companyRequest.getCity());
        existing.setCountry(companyRequest.getCountry());
        existing.setCompanyType(companyRequest.getCompanyType());
        existing.setFoundingDate(companyRequest.getFoundingDate());
        existing.setUpdatedAt(LocalDateTime.now());

        return repositoryPort.save(existing);
    }


}
