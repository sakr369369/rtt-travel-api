package com.roottronics.travelcore.authentication.dto.response;

public record RefreshTokenResponse(

        String accessToken,

        String refreshToken
) {
}
