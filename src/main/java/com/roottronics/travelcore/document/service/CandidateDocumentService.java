package com.roottronics.travelcore.document.service;

import com.roottronics.travelcore.document.dto.request.UploadCandidateDocumentRequest;
import com.roottronics.travelcore.document.dto.response.CandidateDocumentResponse;
import org.springframework.web.multipart.MultipartFile;

public interface CandidateDocumentService {

    /**
     * Upload Candidate Document Metadata
     */
    CandidateDocumentResponse uploadDocument(
            UploadCandidateDocumentRequest request, MultipartFile file);

}