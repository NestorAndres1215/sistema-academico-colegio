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
import java.time.LocalDateTime;
import java.util.List;


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
    public Company findById(String id) {
        return repositoryPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found"));
    }



    @Override
    public Company save(MultipartFile logo, CompanyRequest companyRequest) throws IOException {

        if (logo.isEmpty()) {
            throw new RuntimeException("The file is empty");
        }
        String fileName = fileUseCase.storeFile(logo,"company");

        String fileUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/company/")
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
    public Company update(String id,MultipartFile logo, CompanyRequest companyRequest) throws IOException {

        Company existing = repositoryPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found"));

        if (logo != null && !logo.isEmpty()) {

            if (existing.getLogo() != null && !existing.getLogo().isEmpty()) {
                fileUseCase.deleteFile(existing.getLogo());
            }

            String fileName = fileUseCase.storeFile(logo, "company");

            String fileUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/company/")
                    .path(fileName)
                    .toUriString();

            existing.setLogo(fileUrl);
        }

        existing.setName(companyRequest.getName());
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
