package com.roottronics.travelcore.tenant.controller;

import com.roottronics.travelcore.common.response.BaseResponse;
import com.roottronics.travelcore.tenant.dto.request.CreateTenantRequest;
import com.roottronics.travelcore.tenant.dto.response.TenantResponse;
import com.roottronics.travelcore.tenant.service.TenantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tenants")
@RequiredArgsConstructor
public class TenantController {

    private final TenantService tenantService;

    /**
     * Create Tenant
     */
    @PostMapping
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<BaseResponse<TenantResponse>> createTenant(
            @Valid @RequestBody CreateTenantRequest request) {

        TenantResponse response = tenantService.createTenant(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        BaseResponse.success(
                                "Tenant created successfully.",
                                response
                        )
                );
    }

    /**
     * Get All Tenants
     */
    @GetMapping
    public ResponseEntity<BaseResponse<List<TenantResponse>>> getAllTenants() {

        List<TenantResponse> response = tenantService.getAllTenants();

        return ResponseEntity.ok(
                BaseResponse.success(
                        "Tenant list fetched successfully.",
                        response
                )
        );
    }

}