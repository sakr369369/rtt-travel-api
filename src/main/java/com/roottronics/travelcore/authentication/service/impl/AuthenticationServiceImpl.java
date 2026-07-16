package com.roottronics.travelcore.authentication.service.impl;

import com.roottronics.travelcore.authentication.dto.request.LoginRequest;
import com.roottronics.travelcore.authentication.dto.request.RefreshTokenRequest;
import com.roottronics.travelcore.authentication.dto.response.LoginResponse;
import com.roottronics.travelcore.authentication.dto.response.RefreshTokenResponse;
import com.roottronics.travelcore.authentication.entity.RefreshToken;
import com.roottronics.travelcore.authentication.service.AuthenticationService;
import com.roottronics.travelcore.authentication.service.RefreshTokenService;
import com.roottronics.travelcore.security.jwt.JwtService;
import com.roottronics.travelcore.security.util.JwtConstants;
import com.roottronics.travelcore.user.entity.AppUser;
import com.roottronics.travelcore.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl
        implements AuthenticationService {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final JwtService jwtService;

    private final RefreshTokenService refreshTokenService;

    @Override
    public LoginResponse login(LoginRequest request) {

        authenticationManager.authenticate(

                new UsernamePasswordAuthenticationToken(

                        request.email(),

                        request.password()

                )

        );

        AppUser appUser = userRepository.findByEmail(request.email())

                .orElseThrow(() ->

                        new RuntimeException("Invalid Credentials"));

        User userDetails = new User(

                appUser.getEmail(),

                appUser.getPassword(),

                java.util.List.of(

                        () -> "ROLE_" + appUser.getUserType().name()

                )

        );

        String accessToken =

                jwtService.generateToken(

                        userDetails,

                        appUser

                );

        RefreshToken refreshToken =

                refreshTokenService.createRefreshToken(

                        appUser

                );

        return new LoginResponse(

                accessToken,

                refreshToken.getToken(),

                JwtConstants.TOKEN_TYPE,

                900000L,

                appUser.getId(),

                appUser.getEmployeeCode(),

                appUser.getFirstName(),

                appUser.getLastName(),

                appUser.getEmail(),

                appUser.getUserType()

        );

    }

    @Override
    public RefreshTokenResponse refreshToken(
            RefreshTokenRequest request) {

        RefreshToken refreshToken =

                refreshTokenService.verifyRefreshToken(

                        request.refreshToken()

                );

        AppUser appUser = refreshToken.getAppUser();

        User userDetails = new User(

                appUser.getEmail(),

                appUser.getPassword(),

                java.util.List.of(

                        () -> "ROLE_" + appUser.getUserType().name()

                )

        );

        String accessToken =

                jwtService.generateToken(

                        userDetails,

                        appUser

                );

        return new RefreshTokenResponse(

                accessToken,

                refreshToken.getToken()

        );

    }

    @Override
    public void logout(String refreshToken) {

        refreshTokenService.revokeRefreshToken(

                refreshToken

        );

    }

}