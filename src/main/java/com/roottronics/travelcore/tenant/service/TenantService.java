package com.roottronics.travelcore.tenant.service;

import com.roottronics.travelcore.tenant.dto.request.CreateTenantRequest;
import com.roottronics.travelcore.tenant.dto.response.TenantResponse;

import java.util.List;

public interface TenantService {

    /**
     * Create Tenant
     */
    TenantResponse createTenant(CreateTenantRequest request);

    /**
     * Get All Tenants
     */
    List<TenantResponse> getAllTenants();

}