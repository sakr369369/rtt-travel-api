package com.roottronics.travelcore.authentication.service;

import com.roottronics.travelcore.authentication.entity.RefreshToken;
import com.roottronics.travelcore.user.entity.AppUser;

public interface RefreshTokenService {

    RefreshToken createRefreshToken(AppUser user);

    RefreshToken verifyRefreshToken(String token);

    void revokeRefreshToken(String token);

}
