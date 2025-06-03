package com.nx.spacex.dto;

import java.util.Date;

public record JwtTokenDto(String tokenType, String accessToken, String refreshToken, Date expiresAt) {
}
