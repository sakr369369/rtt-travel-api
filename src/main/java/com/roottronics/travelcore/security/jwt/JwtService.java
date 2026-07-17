package com.roottronics.travelcore.security.jwt;

import com.roottronics.travelcore.security.util.JwtConstants;
import com.roottronics.travelcore.user.entity.AppUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.access-token-expiration}")
    private long accessTokenExpiration;

    private SecretKey secretKey;

    @PostConstruct
    public void init() {

        this.secretKey = Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(secret)
        );

    }

    /**
     * Generate JWT Access Token
     */
    public String generateToken(UserDetails userDetails,
                                AppUser appUser) {

        Map<String, Object> claims = new HashMap<>();

        claims.put(JwtConstants.CLAIM_USER_ID, appUser.getId());

        claims.put(JwtConstants.CLAIM_EMAIL, appUser.getEmail());

        claims.put(JwtConstants.CLAIM_TENANT_ID, appUser.getTenantId());
        claims.put(JwtConstants.CLAIM_COMPANY_ID, appUser.getCompanyId());

        claims.put(JwtConstants.CLAIM_USER_TYPE,
                appUser.getUserType().name());

        claims.put(JwtConstants.CLAIM_EMPLOYEE_CODE,
                appUser.getEmployeeCode());

        claims.put(JwtConstants.CLAIM_AUTHORITIES,
                getAuthorities(userDetails.getAuthorities()));

        return buildToken(claims, userDetails);

    }

    /**
     * Build JWT
     */
    private String buildToken(Map<String, Object> claims,
                              UserDetails userDetails) {

        Date now = new Date();

        Date expiry = new Date(
                now.getTime() + accessTokenExpiration
        );

        return Jwts.builder()

                .claims(claims)

                .subject(userDetails.getUsername())

                .issuedAt(now)

                .expiration(expiry)

                .signWith(secretKey)

                .compact();

    }

    /**
     * Validate Token
     */
    public boolean isTokenValid(String token,
                                UserDetails userDetails) {

        String username = extractUsername(token);

        return username.equals(userDetails.getUsername())
                && !isTokenExpired(token);

    }

    /**
     * Username
     */
    public String extractUsername(String token) {

        return extractClaim(
                token,
                Claims::getSubject
        );

    }

    /**
     * Expiration
     */
    public Date extractExpiration(String token) {

        return extractClaim(
                token,
                Claims::getExpiration
        );

    }

    /**
     * Extract Claim
     */
    public <T> T extractClaim(String token,
                              Function<Claims, T> resolver) {

        Claims claims = extractAllClaims(token);

        return resolver.apply(claims);

    }

    /**
     * Extract All Claims
     */
    private Claims extractAllClaims(String token) {

        return Jwts.parser()

                .verifyWith(secretKey)

                .build()

                .parseSignedClaims(token)

                .getPayload();

    }

    /**
     * Token Expired
     */
    private boolean isTokenExpired(String token) {

        return extractExpiration(token)
                .before(new Date());

    }

    /**
     * Convert Authorities
     */
    private String getAuthorities(
            Collection<? extends GrantedAuthority> authorities) {

        return authorities.stream()

                .map(GrantedAuthority::getAuthority)

                .collect(Collectors.joining(","));

    }

}