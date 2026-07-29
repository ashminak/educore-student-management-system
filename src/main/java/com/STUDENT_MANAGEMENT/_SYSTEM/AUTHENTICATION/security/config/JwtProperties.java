package com.STUDENT_MANAGEMENT._SYSTEM.AUTHENTICATION.security.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    private String secret;// secret key used to sigh jwt

    private Long expiration;//access token expiration

    private Long refreshExpiration;//refresh token expiration

    private String issuer;//jwt issuer

}
