package com.roottronics.travelcore.security.util;

public final class JwtConstants {

    private JwtConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String AUTHORIZATION = "Authorization";

    public static final String BEARER = "Bearer ";

    public static final String TOKEN_TYPE = "JWT";

    public static final String CLAIM_USER_ID = "userId";

    public static final String CLAIM_AUTHORITIES = "authorities";

    public static final String CLAIM_USER_TYPE = "userType";

    public static final String CLAIM_EMAIL = "email";

    public static final String ISSUER = "RTTTravelCore";

    public static final String AUDIENCE = "RTTTravelCore-Users";

}