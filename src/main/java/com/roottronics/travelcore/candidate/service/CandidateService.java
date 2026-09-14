package com.roottronics.travelcore.candidate.service;

import com.roottronics.travelcore.candidate.dto.request.CreateCandidateRequest;
import com.roottronics.travelcore.candidate.dto.response.CandidateResponse;

import java.util.List;

public interface CandidateService {

    /**
     * Create Candidate
     */
    CandidateResponse createCandidate(CreateCandidateRequest request);

    List<CandidateResponse> fetchAllCandidates();

    CandidateResponse updateCandidate(
            Long candidateId,
            CreateCandidateRequest request);

    void deleteCandidate(Long candidateId);

}
