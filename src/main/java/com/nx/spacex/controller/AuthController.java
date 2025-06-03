package com.nx.spacex.controller;

import com.nx.spacex.dto.JwtTokenDto;
import com.nx.spacex.dto.LoginDto;
import com.nx.spacex.dto.RefreshTokenDto;
import com.nx.spacex.security.service.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final JwtService jwtService;
    private final AuthenticationManager authManager;
    private final UserDetailsService userDetailsService;


    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JwtTokenDto> login(@RequestBody LoginDto dto) {
        final Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.username(), dto.password())
        );

        final UserDetails userDetails = (UserDetails) auth.getPrincipal();

        final String accessJwt = jwtService.generateAccessToken(userDetails);
        final String refreshJwt = jwtService.generateRefreshToken(userDetails);
        final Date expiredAt = jwtService.getExpirationDate();

        return ResponseEntity.ok(new JwtTokenDto("Bearer", accessJwt, refreshJwt, expiredAt));
    }

    @PostMapping("/refresh")
    public ResponseEntity<Object> refresh(@RequestBody RefreshTokenDto req) {
        final String refreshJwt = req.refreshToken();

        final String userName = jwtService.extractUsername(refreshJwt);
        final UserDetails user = userDetailsService.loadUserByUsername(userName);

        if (jwtService.isTokenValid(refreshJwt, user)) {
            final String accessJwt = jwtService.generateAccessToken(user);
            final Date expiredAt = jwtService.getExpirationDate();

            return ResponseEntity.ok(new JwtTokenDto("Bearer", accessJwt, refreshJwt, expiredAt));
        }

        return ResponseEntity.badRequest().body("Invalid Refresh Token");
    }
}
