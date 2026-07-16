package com.roottronics.travelcore.authentication.dto.response;

import com.roottronics.travelcore.user.enums.UserType;

public record LoginResponse(
        String accessToken,

        String refreshToken,

        String tokenType,

        Long expiresIn,

        Long userId,

        String employeeCode,

        String firstName,

        String lastName,

        String email,

        UserType userType
) {
}
