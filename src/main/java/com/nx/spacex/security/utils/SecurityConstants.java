package com.nx.spacex.security.utils;

import org.springframework.beans.factory.annotation.Value;

import java.time.Duration;

public class SecurityConstants {

  @Value("${app.jwt.secret}")
  public static String SECRET_KEY;

  public static final long EXPIRATION_TIME = Duration.ofHours(4).toMillis();
  public static final String TOKEN_PREFIX = "Bearer ";
  public static final String HEADER_STRING = "Authorization";
  public static final String HEADER_USER_ID = "UserId";

  public static final String LOGIN_URL = "/users/login";
  public static final String SIGN_UP_URL = "/users";

}
