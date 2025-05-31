package com.nx.spacex.security.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.nx.spacex.security.utils.SecretConvertUtils.secretToSecretKey;
import static com.nx.spacex.security.utils.SecurityConstants.EXPIRATION_TIME;

@Service
@NoArgsConstructor
public class JwtService {

    @Value("${app.jwt.secret}")
    private String SECRET_KEY;

    public String generateToken(UserDetails user) {
        return Jwts.builder()
                .subject(user.getUsername())
                .claim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(secretToSecretKey(SECRET_KEY))
                .compact();
    }

    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    public boolean isTokenValid(String token, UserDetails user) {
        return extractUsername(token).equals(user.getUsername()) && !getClaims(token).getExpiration().before(new Date());
    }

    private Claims getClaims(String authToken) {
        final JwtParser jwtParser = Jwts.parser().verifyWith(secretToSecretKey(SECRET_KEY)).build();
        return jwtParser.parseSignedClaims(authToken).getPayload();
    }
}

