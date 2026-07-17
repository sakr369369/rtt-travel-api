package com.roottronics.travelcore.company.service;

import com.roottronics.travelcore.company.dto.request.CreateCompanyRequest;
import com.roottronics.travelcore.company.dto.response.CompanyResponse;

public interface CompanyService {

    /**
     * Create Company
     */
    CompanyResponse createCompany(CreateCompanyRequest request);

}