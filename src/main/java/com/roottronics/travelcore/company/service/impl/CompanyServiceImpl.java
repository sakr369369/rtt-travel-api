package com.roottronics.travelcore.company.service.impl;

import com.roottronics.travelcore.common.enums.Status;
import com.roottronics.travelcore.company.dto.request.CreateCompanyRequest;
import com.roottronics.travelcore.company.dto.response.CompanyResponse;
import com.roottronics.travelcore.company.entity.Company;
import com.roottronics.travelcore.company.mapper.CompanyMapper;
import com.roottronics.travelcore.company.repository.CompanyRepository;
import com.roottronics.travelcore.company.service.CompanyService;
import com.roottronics.travelcore.exception.ResourceAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    private final CompanyMapper companyMapper;

    @Override
    public CompanyResponse createCompany(CreateCompanyRequest request) {

        validateCompany(request);

        Company company = companyMapper.toEntity(request);


        company.setStatus(Status.ACTIVE);

        Company savedCompany = companyRepository.save(company);

        return companyMapper.toResponse(savedCompany);

    }

    /**
     * Validate Duplicate Company
     */
    private void validateCompany(CreateCompanyRequest request ) {

        if (companyRepository.existsByCompanyCode(
                request.companyCode())) {

            throw new ResourceAlreadyExistsException(
                    "Company Code already exists."
            );

        }

        if (companyRepository.existsByCompanyName(
                request.companyName())) {

            throw new ResourceAlreadyExistsException(
                    "Company Name already exists."
            );

        }

        if (companyRepository.existsByEmail(
                request.email())) {

            throw new ResourceAlreadyExistsException(
                    "Email already exists."
            );

        }

        if (companyRepository.existsByMobile(
                request.mobile())) {

            throw new ResourceAlreadyExistsException(
                    "Mobile Number already exists."
            );

        }

    }


    @Override
    public List<CompanyResponse> getAllCompanies() {

        return companyRepository.findAll()
                .stream()
                .map(company -> CompanyResponse.builder()
                        .id(company.getId())
                        .companyCode(company.getCompanyCode())
                        .companyName(company.getCompanyName())
                        .contactPerson(company.getContactPerson())
                        .email(company.getEmail())
                        .mobile(company.getMobile())
                        .country(company.getCountry())
                        .state(company.getState())
                        .city(company.getCity())
                        .address(company.getAddress())
                        .status(company.getStatus())
                        .createdDate(company.getCreatedDate())
                        .updatedDate(company.getUpdatedDate())
                        .build())
                .toList();
    }

}
