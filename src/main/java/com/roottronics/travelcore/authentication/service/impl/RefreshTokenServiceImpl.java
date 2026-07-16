package com.roottronics.travelcore.authentication.service.impl;

import com.roottronics.travelcore.authentication.entity.RefreshToken;
import com.roottronics.travelcore.authentication.repository.RefreshTokenRepository;
import com.roottronics.travelcore.authentication.service.RefreshTokenService;
import com.roottronics.travelcore.user.entity.AppUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    @Value("${jwt.refresh-token-expiration}")
    private long refreshTokenExpiration;

    @Override
    public RefreshToken createRefreshToken(AppUser user) {

        RefreshToken refreshToken = refreshTokenRepository
                .findByAppUser(user)
                .orElse(new RefreshToken());

        refreshToken.setAppUser(user);

        refreshToken.setToken(UUID.randomUUID().toString());


        refreshToken.setExpiryDate(
                LocalDateTime.now()
                        .plusSeconds(refreshTokenExpiration / 1000));

        refreshToken.setRevoked(false);

        return refreshTokenRepository.save(refreshToken);

    }

    @Override
    public RefreshToken verifyRefreshToken(String token) {

        RefreshToken refreshToken =
                refreshTokenRepository.findByToken(token)
                        .orElseThrow(() ->
                                new RuntimeException("Invalid Refresh Token"));

        if (refreshToken.getRevoked()) {

            throw new RuntimeException("Refresh Token Revoked");

        }

        if (refreshToken.getExpiryDate().isBefore(LocalDateTime.now())) {

            throw new RuntimeException("Refresh Token Expired");

        }

        return refreshToken;

    }

    @Override
    public void revokeRefreshToken(String token) {

        RefreshToken refreshToken =
                verifyRefreshToken(token);

        refreshToken.setRevoked(true);

        refreshTokenRepository.save(refreshToken);

    }

}