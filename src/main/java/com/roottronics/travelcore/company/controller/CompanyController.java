package com.roottronics.travelcore.company.controller;

import com.roottronics.travelcore.common.response.BaseResponse;
import com.roottronics.travelcore.company.dto.request.CreateCompanyRequest;
import com.roottronics.travelcore.company.dto.response.CompanyResponse;
import com.roottronics.travelcore.company.service.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    /**
     * Create Company
     */
    @PostMapping
    public ResponseEntity<BaseResponse<CompanyResponse>> createCompany(
            @Valid @RequestBody CreateCompanyRequest request) {

        CompanyResponse response =
                companyService.createCompany(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        BaseResponse.success(
                                "Company created successfully.",
                                response
                        )
                );
    }
    @GetMapping
    public ResponseEntity<BaseResponse<List<CompanyResponse>>> getAllCompanies() {

        List<CompanyResponse> companies = companyService.getAllCompanies();

        return ResponseEntity.ok(
                BaseResponse.success(
                        "Companies fetched successfully.",
                        companies
                )
        );
    }

    @PutMapping("/{companyId}")
    public ResponseEntity<BaseResponse<CompanyResponse>> updateCompany(
            @PathVariable Long companyId,
            @Valid @RequestBody CreateCompanyRequest request) {

        CompanyResponse response =
                companyService.updateCompany(companyId, request);

        return ResponseEntity.ok(
                BaseResponse.success(
                        "Company updated successfully.",
                        response
                )
        );
    }

    @DeleteMapping("/{companyId}")
    public ResponseEntity<BaseResponse<Void>> deleteCompany(
            @PathVariable Long companyId) {

        companyService.deleteCompany(companyId);

        return ResponseEntity.ok(
                BaseResponse.success(
                        "Company deleted successfully.",
                        null
                )
        );
    }
}