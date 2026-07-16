package com.roottronics.travelcore.authentication.repository;

import com.roottronics.travelcore.authentication.entity.RefreshToken;
import com.roottronics.travelcore.user.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository
        extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);

    Optional<RefreshToken> findByAppUser(AppUser appUser);
}