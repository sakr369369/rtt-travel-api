package com.roottronics.travelcore.company.dto.response;

import com.roottronics.travelcore.common.enums.Status;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CompanyResponse(

        Long id,

        Long tenantId,

        String companyCode,

        String companyName,

        String contactPerson,

        String email,

        String mobile,

        String country,

        String state,

        String city,

        String address,

        Status status,

        LocalDateTime createdDate,

        LocalDateTime updatedDate

) {
}