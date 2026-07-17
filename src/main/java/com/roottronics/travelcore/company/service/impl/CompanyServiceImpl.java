package com.roottronics.travelcore.company.service.impl;

import com.roottronics.travelcore.common.enums.Status;
import com.roottronics.travelcore.company.dto.request.CreateCompanyRequest;
import com.roottronics.travelcore.company.dto.response.CompanyResponse;
import com.roottronics.travelcore.company.entity.Company;
import com.roottronics.travelcore.company.mapper.CompanyMapper;
import com.roottronics.travelcore.company.repository.CompanyRepository;
import com.roottronics.travelcore.company.service.CompanyService;
import com.roottronics.travelcore.exception.ResourceAlreadyExistsException;
import com.roottronics.travelcore.security.userdetails.CustomUserDetails;
import com.roottronics.travelcore.security.userdetails.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    private final CompanyMapper companyMapper;

    @Override
    public CompanyResponse createCompany(CreateCompanyRequest request) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        CustomUserDetails currentUser =
                (CustomUserDetails) authentication.getPrincipal();

        Long tenantId = currentUser.getTenantId();

        validateCompany(request, tenantId);

        Company company = companyMapper.toEntity(request);

        company.setTenantId(tenantId);

        company.setStatus(Status.ACTIVE);

        Company savedCompany = companyRepository.save(company);

        return companyMapper.toResponse(savedCompany);

    }

    /**
     * Validate Duplicate Company
     */
    private void validateCompany(CreateCompanyRequest request,
                                 Long tenantId) {

        if (companyRepository.existsByCompanyCodeAndTenantId(
                request.companyCode(),
                tenantId)) {

            throw new ResourceAlreadyExistsException(
                    "Company Code already exists."
            );

        }

        if (companyRepository.existsByCompanyNameAndTenantId(
                request.companyName(),
                tenantId)) {

            throw new ResourceAlreadyExistsException(
                    "Company Name already exists."
            );

        }

        if (companyRepository.existsByEmailAndTenantId(
                request.email(),
                tenantId)) {

            throw new ResourceAlreadyExistsException(
                    "Email already exists."
            );

        }

        if (companyRepository.existsByMobileAndTenantId(
                request.mobile(),
                tenantId)) {

            throw new ResourceAlreadyExistsException(
                    "Mobile Number already exists."
            );

        }

    }

}