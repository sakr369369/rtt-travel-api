package com.roottronics.travelcore.company.mapper;

import com.roottronics.travelcore.common.enums.Status;
import com.roottronics.travelcore.company.dto.request.CreateCompanyRequest;
import com.roottronics.travelcore.company.dto.response.CompanyResponse;
import com.roottronics.travelcore.company.entity.Company;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {

    /**
     * Request -> Entity
     */
    public Company toEntity(CreateCompanyRequest request) {

        Company company = new Company();

        company.setCompanyCode(request.companyCode());
        company.setCompanyName(request.companyName());
        company.setContactPerson(request.contactPerson());
        company.setEmail(request.email());
        company.setMobile(request.mobile());
        company.setCountry(request.country());
        company.setState(request.state());
        company.setCity(request.city());
        company.setAddress(request.address());

        // tenantId will be set from JWT in Service Layer
        // company.setTenantId(...);

        company.setStatus(Status.ACTIVE);

        return company;
    }

    /**
     * Entity -> Response
     */
    public CompanyResponse toResponse(Company company) {

        return CompanyResponse.builder()
                .id(company.getId())
                .tenantId(company.getTenantId())
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
                .createdDate(company.getCreatedAt())
                .updatedDate(company.getUpdatedAt())
                .build();

    }

}