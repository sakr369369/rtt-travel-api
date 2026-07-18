package com.roottronics.travelcore.document.dto.response;

import com.roottronics.travelcore.common.enums.Status;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
public record CandidateDocumentResponse(

        Long id,

        Long tenantId,

        Long companyId,

        Long candidateId,

        Long documentTypeId,

        String documentType,

        String documentNumber,

        LocalDate issueDate,

        LocalDate expiryDate,

        String fileName,

        String filePath,

        Long fileSize,

        String contentType,

        String remarks,

        Status status,

        LocalDateTime createdDate,

        LocalDateTime updatedDate

) {
}