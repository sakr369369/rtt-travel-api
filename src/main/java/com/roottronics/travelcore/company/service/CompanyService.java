package com.roottronics.travelcore.company.service;

import com.roottronics.travelcore.company.dto.request.CreateCompanyRequest;
import com.roottronics.travelcore.company.dto.response.CompanyResponse;

import java.util.List;

public interface CompanyService {

    /**
     * Create Company
     */
    CompanyResponse createCompany(CreateCompanyRequest request);

    List<CompanyResponse> getAllCompanies();
}