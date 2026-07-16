package com.roottronics.travelcore.tenant.dto.response;

import com.roottronics.travelcore.common.enums.Status;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record TenantResponse(

        Long id,

        String tenantCode,

        String tenantName,

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