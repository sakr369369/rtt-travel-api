package com.roottronics.travelcore.candidate.controller;

import com.roottronics.travelcore.candidate.dto.request.CreateCandidateRequest;
import com.roottronics.travelcore.candidate.dto.response.CandidateResponse;
import com.roottronics.travelcore.candidate.service.CandidateService;
import com.roottronics.travelcore.common.response.BaseResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/candidates")
@RequiredArgsConstructor
public class CandidateController {

    private final CandidateService candidateService;

    /**
     * Create Candidate
     */
    @PostMapping
    @PreAuthorize("hasAnyRole('SUPER_ADMIN','EMPLOYEE')")
    public ResponseEntity<BaseResponse<CandidateResponse>> createCandidate(
            @Valid @RequestBody CreateCandidateRequest request) {

        CandidateResponse response =
                candidateService.createCandidate(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        BaseResponse.success(
                                "Candidate created successfully.",
                                response
                        )
                );
    }

}