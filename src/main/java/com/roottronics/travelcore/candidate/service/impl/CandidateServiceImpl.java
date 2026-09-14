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
import com.roottronics.travelcore.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository candidateRepository;

    private final CompanyRepository companyRepository;

    private final CandidateMapper candidateMapper;

    @Override
    public CandidateResponse createCandidate(CreateCandidateRequest request) {

        Company company = companyRepository.findById(request.companyId())
                .orElseThrow(() ->
                        new BadRequestException("Company not found."));

        candidateRepository
                .findByCandidateCode(request.candidateCode())
                .ifPresent(candidate -> {
                    throw new BadRequestException(
                            "Candidate Code already exists.");
                });

        candidateRepository
                .findByPassportNumber(request.passportNumber())
                .ifPresent(candidate -> {
                    throw new BadRequestException(
                            "Passport Number already exists.");
                });

        candidateRepository
                .findByEmail(request.email())
                .ifPresent(candidate -> {
                    throw new BadRequestException(
                            "Email already exists.");
                });

        candidateRepository
                .findByMobile(request.mobile())
                .ifPresent(candidate -> {
                    throw new BadRequestException(
                            "Mobile Number already exists.");
                });

        Candidate candidate =
                candidateMapper.toEntity(request);

        Candidate savedCandidate =
                candidateRepository.save(candidate);

        return candidateMapper.toResponse(savedCandidate);

    }

    @Override
    public List<CandidateResponse> fetchAllCandidates() {

        return candidateRepository.findAllByDeletedFalse()
                .stream()
                .map(candidateMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public CandidateResponse updateCandidate(
            Long candidateId,
            CreateCandidateRequest request) {

        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Candidate not found with id: " + candidateId));

        candidateMapper.updateEntity(request, candidate);

        Candidate updatedCandidate = candidateRepository.save(candidate);

        return candidateMapper.toResponse(updatedCandidate);
    }

    @Override
    @Transactional
    public void deleteCandidate(Long candidateId) {

        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Candidate not found with id: " + candidateId));

        candidate.setDeleted(true);

        candidateRepository.save(candidate);
    }

}
