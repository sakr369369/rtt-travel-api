package com.roottronics.travelcore.document.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record UploadCandidateDocumentRequest(

      /*  @NotNull(message = "Company Id is required")
        Long companyId,*/

        @NotNull(message = "Candidate Id is required")
        Long candidateId,

        @NotNull(message = "Document Type Id is required")
        Long documentTypeId,

        @Size(max = 100, message = "Document Number must not exceed 100 characters")
        String documentNumber,

        LocalDate issueDate,

        LocalDate expiryDate,

        @Size(max = 500, message = "Remarks must not exceed 500 characters")
        String remarks

) {
}