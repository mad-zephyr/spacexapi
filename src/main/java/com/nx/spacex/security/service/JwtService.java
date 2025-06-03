package com.nx.spacex.security.service;

import com.nx.spacex.security.utils.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

import static com.nx.spacex.security.utils.SecretConvertUtils.secretToSecretKey;
import static com.nx.spacex.security.utils.SecurityConstants.EXPIRATION_TIME;
import static com.nx.spacex.security.utils.SecurityConstants.VALIDITY_TIME;

@Service
@AllArgsConstructor
public class JwtService {

    private final SecurityConstants constants;

    public Date getExpirationDate() {
        final Date now = new Date();
        return new Date(now.getTime() + EXPIRATION_TIME);
    }

    public String generateAccessToken(UserDetails user) {
        final Date now = new Date();
        final Date expiry = getExpirationDate();

        return Jwts.builder()
                .subject(user.getUsername())
                .claim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                .issuedAt(now)
                .expiration(expiry)
                .signWith(secretToSecretKey(constants.SECRET_KEY))
                .compact();
    }

    public String generateRefreshToken(UserDetails user) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + VALIDITY_TIME);

        return Jwts.builder()
                .id(UUID.randomUUID().toString())
                .subject(user.getUsername())
                .issuedAt(now)
                .expiration(expiry)
                .signWith(secretToSecretKey(constants.SECRET_KEY))
                .compact();
    }

    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    public boolean isTokenValid(String token, UserDetails user) {
        return extractUsername(token).equals(user.getUsername()) && !getClaims(token).getExpiration().before(new Date());
    }

    private Claims getClaims(String authToken) {
        final JwtParser jwtParser = Jwts.parser().verifyWith(secretToSecretKey(constants.SECRET_KEY)).build();
        return jwtParser.parseSignedClaims(authToken).getPayload();
    }
}

