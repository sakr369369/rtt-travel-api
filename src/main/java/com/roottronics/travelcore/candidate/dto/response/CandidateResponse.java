package com.roottronics.travelcore.candidate.dto.response;

import com.roottronics.travelcore.common.enums.Status;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
public record CandidateResponse(

        Long id,

        Long tenantId,

        Long companyId,

        String candidateCode,

        String firstName,

        String lastName,

        String gender,

        LocalDate dateOfBirth,

        String passportNumber,

        LocalDate passportExpiryDate,

        String nationality,

        String email,

        String mobile,

        String address,

        Status status,

        LocalDateTime createdDate,

        LocalDateTime updatedDate

) {
}