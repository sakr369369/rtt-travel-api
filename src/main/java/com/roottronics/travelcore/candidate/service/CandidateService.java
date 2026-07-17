package com.roottronics.travelcore.candidate.service;

import com.roottronics.travelcore.candidate.dto.request.CreateCandidateRequest;
import com.roottronics.travelcore.candidate.dto.response.CandidateResponse;

public interface CandidateService {

    /**
     * Create Candidate
     */
    CandidateResponse createCandidate(CreateCandidateRequest request);

}