package com.roottronics.travelcore.authentication.service;

import com.roottronics.travelcore.authentication.dto.request.LoginRequest;
import com.roottronics.travelcore.authentication.dto.request.RefreshTokenRequest;
import com.roottronics.travelcore.authentication.dto.response.LoginResponse;
import com.roottronics.travelcore.authentication.dto.response.RefreshTokenResponse;

public interface AuthenticationService {

    LoginResponse login(LoginRequest request);

    RefreshTokenResponse refreshToken(
            RefreshTokenRequest request);

    void logout(String refreshToken);
}