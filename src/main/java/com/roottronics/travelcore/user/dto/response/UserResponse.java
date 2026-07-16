package com.roottronics.travelcore.user.dto.response;

import com.roottronics.travelcore.common.enums.Status;
import com.roottronics.travelcore.user.enums.UserType;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record UserResponse(

        Long id,

        String employeeCode,

        String firstName,

        String lastName,

        String email,

        String mobile,

        UserType userType,

        Status status,

        LocalDateTime createdAt

) {
}