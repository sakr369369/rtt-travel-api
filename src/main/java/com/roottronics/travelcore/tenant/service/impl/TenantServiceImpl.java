package com.roottronics.travelcore.tenant.service.impl;

import com.roottronics.travelcore.common.enums.Status;
import com.roottronics.travelcore.exception.ResourceAlreadyExistsException;
import com.roottronics.travelcore.tenant.dto.request.CreateTenantRequest;
import com.roottronics.travelcore.tenant.dto.response.TenantResponse;
import com.roottronics.travelcore.tenant.entity.Tenant;
import com.roottronics.travelcore.tenant.mapper.TenantMapper;
import com.roottronics.travelcore.tenant.repository.TenantRepository;
import com.roottronics.travelcore.tenant.service.TenantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TenantServiceImpl implements TenantService {

    private final TenantRepository tenantRepository;

    private final TenantMapper tenantMapper;

    /**
     * Create Tenant
     */
    @Override
    public TenantResponse createTenant(CreateTenantRequest request) {

        validateTenant(request);

        Tenant tenant = tenantMapper.toEntity(request);

        tenant.setStatus(Status.ACTIVE);

        Tenant savedTenant = tenantRepository.save(tenant);

        return tenantMapper.toResponse(savedTenant);

    }

    /**
     * Get All Tenants
     */
    @Override
    @Transactional(readOnly = true)
    public List<TenantResponse> getAllTenants() {

        return tenantRepository.findAll()
                .stream()
                .map(tenantMapper::toResponse)
                .toList();

    }

    /**
     * Validate Tenant
     */
    private void validateTenant(CreateTenantRequest request) {

       /* if (tenantRepository.existsByTenantCode(request.tenantCode())) {

            throw new ResourceAlreadyExistsException(
                    "Tenant Code already exists."
            );

        }*/

        if (tenantRepository.existsByEmployeeCode(request.employeeCode())) {

            throw new ResourceAlreadyExistsException(
                    "Tenant Employee Code already exists."
            );

        }

        if (tenantRepository.existsByTenantName(request.tenantName())) {

            throw new ResourceAlreadyExistsException(
                    "Tenant Name already exists."
            );

        }

        if (tenantRepository.existsByEmail(request.email())) {

            throw new ResourceAlreadyExistsException(
                    "Email already exists."
            );

        }

        if (tenantRepository.existsByMobile(request.mobile())) {

            throw new ResourceAlreadyExistsException(
                    "Mobile Number already exists."
            );

        }

    }

}