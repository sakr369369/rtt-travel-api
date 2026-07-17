package com.roottronics.travelcore.candidate.service.impl;

import com.roottronics.travelcore.candidate.dto.request.CreateCandidateRequest;
import com.roottronics.travelcore.candidate.dto.response.CandidateResponse;
import com.roottronics.travelcore.candidate.entity.Candidate;
import com.roottronics.travelcore.candidate.mapper.CandidateMapper;
import com.roottronics.travelcore.candidate.repository.CandidateRepository;
import com.roottronics.travelcore.candidate.service.CandidateService;
import com.roottronics.travelcore.exception.BadRequestException;
import com.roottronics.travelcore.company.entity.Company;
import com.roottronics.travelcore.company.repository.CompanyRepository;
import com.roottronics.travelcore.security.userdetails.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository candidateRepository;

    private final CompanyRepository companyRepository;

    private final CandidateMapper candidateMapper;

    @Override
    public CandidateResponse createCandidate(CreateCandidateRequest request) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        CustomUserDetails currentUser =
                (CustomUserDetails) authentication.getPrincipal();

        Long tenantId = currentUser.getTenantId();

        Company company = companyRepository.findById(request.companyId())
                .orElseThrow(() ->
                        new BadRequestException("Company not found."));

        if (!company.getTenantId().equals(tenantId)) {
            throw new BadRequestException(
                    "Company does not belong to the logged-in tenant.");
        }

        candidateRepository
                .findByCandidateCodeAndTenantId(
                        request.candidateCode(),
                        tenantId)
                .ifPresent(candidate -> {
                    throw new BadRequestException(
                            "Candidate Code already exists.");
                });

        candidateRepository
                .findByPassportNumberAndTenantId(
                        request.passportNumber(),
                        tenantId)
                .ifPresent(candidate -> {
                    throw new BadRequestException(
                            "Passport Number already exists.");
                });

        candidateRepository
                .findByEmailAndTenantId(
                        request.email(),
                        tenantId)
                .ifPresent(candidate -> {
                    throw new BadRequestException(
                            "Email already exists.");
                });

        candidateRepository
                .findByMobileAndTenantId(
                        request.mobile(),
                        tenantId)
                .ifPresent(candidate -> {
                    throw new BadRequestException(
                            "Mobile Number already exists.");
                });

        Candidate candidate =
                candidateMapper.toEntity(request);

        candidate.setTenantId(tenantId);

        Candidate savedCandidate =
                candidateRepository.save(candidate);

        return candidateMapper.toResponse(savedCandidate);

    }

}