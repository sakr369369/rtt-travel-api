package com.roottronics.travelcore.document.mapper;

import com.roottronics.travelcore.document.dto.request.UploadCandidateDocumentRequest;
import com.roottronics.travelcore.document.dto.response.CandidateDocumentResponse;
import com.roottronics.travelcore.document.entity.CandidateDocument;
import org.springframework.stereotype.Component;

@Component
public class CandidateDocumentMapper {

    /**
     * Request -> Entity
     */
    public CandidateDocument toEntity(
            UploadCandidateDocumentRequest request) {

        CandidateDocument document = new CandidateDocument();

        document.setDocumentNumber(request.documentNumber());

        document.setIssueDate(request.issueDate());

        document.setExpiryDate(request.expiryDate());

        document.setRemarks(request.remarks());

        return document;
    }

    /**
     * Entity -> Response
     */
    public CandidateDocumentResponse toResponse(
            CandidateDocument document) {

        return CandidateDocumentResponse.builder()

                .id(document.getId())

                .tenantId(document.getTenantId())

                .companyId(document.getCompany().getId())

                .candidateId(document.getCandidate().getId())

                .documentTypeId(document.getDocumentType().getId())

                .documentType(document.getDocumentType().getDocumentName())

                .documentNumber(document.getDocumentNumber())

                .issueDate(document.getIssueDate())

                .expiryDate(document.getExpiryDate())

                .fileName(document.getFileName())

                .filePath(document.getFilePath())

                .fileSize(document.getFileSize())

                .contentType(document.getContentType())

                .remarks(document.getRemarks())

                .status(document.getStatus())

                .createdDate(document.getCreatedAt())

                .updatedDate(document.getUpdatedAt())

                .build();
    }

}