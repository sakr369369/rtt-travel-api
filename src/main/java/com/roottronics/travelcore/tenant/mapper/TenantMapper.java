package com.roottronics.travelcore.tenant.mapper;

import com.roottronics.travelcore.common.enums.Status;
import com.roottronics.travelcore.tenant.dto.request.CreateTenantRequest;
import com.roottronics.travelcore.tenant.dto.response.TenantResponse;
import com.roottronics.travelcore.tenant.entity.Tenant;
import org.springframework.stereotype.Component;

@Component
public class TenantMapper {

    /**
     * Request -> Entity
     */
    public Tenant toEntity(CreateTenantRequest request) {

        Tenant tenant = new Tenant();

        //tenant.setTenantCode(request.tenantCode());
        tenant.setEmployeeCode(request.employeeCode());
        tenant.setTenantName(request.tenantName());
        tenant.setContactPerson(request.contactPerson());
        tenant.setEmail(request.email());
        tenant.setMobile(request.mobile());
        tenant.setCountry(request.country());
        tenant.setState(request.state());
        tenant.setCity(request.city());
        tenant.setAddress(request.address());

        tenant.setStatus(Status.ACTIVE);

        return tenant;
    }

    /**
     * Entity -> Response
     */
    public TenantResponse toResponse(Tenant tenant) {

        return TenantResponse.builder()
                .id(tenant.getId())
                //.tenantCode(tenant.getTenantCode())
                .employeeCode(tenant.getEmployeeCode())
                .tenantName(tenant.getTenantName())
                .contactPerson(tenant.getContactPerson())
                .email(tenant.getEmail())
                .mobile(tenant.getMobile())
                .country(tenant.getCountry())
                .state(tenant.getState())
                .city(tenant.getCity())
                .address(tenant.getAddress())
                .status(tenant.getStatus())
                .createdDate(tenant.getCreatedAt())
                .updatedDate(tenant.getUpdatedAt())
                .build();

    }

}