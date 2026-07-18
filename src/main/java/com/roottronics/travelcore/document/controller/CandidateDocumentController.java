package com.roottronics.travelcore.document.controller;

import com.roottronics.travelcore.common.response.BaseResponse;
import com.roottronics.travelcore.document.dto.request.UploadCandidateDocumentRequest;
import com.roottronics.travelcore.document.dto.response.CandidateDocumentResponse;
import com.roottronics.travelcore.document.service.CandidateDocumentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/candidate-documents")
@RequiredArgsConstructor
public class CandidateDocumentController {

    private final CandidateDocumentService candidateDocumentService;

    /**
     * Upload Candidate Document Metadata
     */

    @PostMapping(
            value = "/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    @PreAuthorize("hasAnyRole('SUPER_ADMIN','EMPLOYEE')")
    public ResponseEntity<BaseResponse<CandidateDocumentResponse>> uploadDocument(

            @Valid @ModelAttribute UploadCandidateDocumentRequest request,

            @RequestParam("file") MultipartFile file) {

        CandidateDocumentResponse response =
                candidateDocumentService.uploadDocument(request, file);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        BaseResponse.success(
                                "Candidate document uploaded successfully.",
                                response
                        )
                );
    }

    }