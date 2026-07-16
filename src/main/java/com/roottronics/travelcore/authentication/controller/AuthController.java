package com.roottronics.travelcore.authentication.controller;

import com.roottronics.travelcore.authentication.dto.request.LoginRequest;
import com.roottronics.travelcore.authentication.dto.request.RefreshTokenRequest;
import com.roottronics.travelcore.authentication.dto.response.LoginResponse;
import com.roottronics.travelcore.authentication.dto.response.RefreshTokenResponse;
import com.roottronics.travelcore.authentication.service.AuthenticationService;
import com.roottronics.travelcore.common.response.BaseResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest request) {

        return ResponseEntity.ok(
                authenticationService.login(request)
        );

    }

    @PostMapping("/refresh")
    public ResponseEntity<RefreshTokenResponse> refreshToken(
            @Valid @RequestBody RefreshTokenRequest request) {

        return ResponseEntity.ok(
                authenticationService.refreshToken(request)
        );

    }

    @PostMapping("/logout")
    public ResponseEntity<BaseResponse<Void>> logout(
            @RequestBody RefreshTokenRequest request) {

        authenticationService.logout(request.refreshToken());

        return ResponseEntity.ok(
                BaseResponse.success("Logout successful", null)
        );
    }

}