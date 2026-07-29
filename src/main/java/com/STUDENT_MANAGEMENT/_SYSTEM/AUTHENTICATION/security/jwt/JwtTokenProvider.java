package com.STUDENT_MANAGEMENT._SYSTEM.AUTHENTICATION.security.jwt;

import com.STUDENT_MANAGEMENT._SYSTEM.AUTHENTICATION.security.config.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    private final JwtProperties  jwtProperties;
    public SecretKey getSigningKey(){
        return Keys.hmacShaKeyFor(
                jwtProperties.getSecret()
                        .getBytes(StandardCharsets.UTF_8)
        );
    }
    public String generateAccessToken(String username){
        Date now = new Date();
        Date expiry = new Date(
                now.getTime()+jwtProperties.getExpiration()
        );
        return Jwts.builder()
                .subject(username)
                .issuer(jwtProperties.getIssuer())
                .issuedAt(now)
                .expiration(expiry)
                .claim("type",JwtTokenType.ACCESS)
                .signWith(getSigningKey())
                .compact();

    }
    public String generateRefreshToken(String username){
        Date now = new Date();
        Date expiry = new Date(
                now.getTime()+jwtProperties.getRefreshExpiration()
        );
        return Jwts.builder()
                .subject(username)
                .issuer(jwtProperties.getIssuer())
                .issuedAt(now)
                .expiration(expiry)
                .claim("type",JwtTokenType.REFRESH)
                .signWith(getSigningKey())
                .compact();
    }
    public Claims getClaims(String token){
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    public String extractUsername(String token){
        return getClaims(token).getSubject();
    }
    public Date extractExpiration(String token){
        return getClaims(token).getExpiration();
    }
    public <T> T  extractClaim(
            String token,
            Function<Claims, T> resolver){
        return resolver.apply(getClaims(token));
    }
    public boolean isTokenExpired(String token){
        return extractExpiration(token)
                .before(new Date());
    }
    public boolean isTokenValid(String token){
        try{
            getClaims(token);
            return true;
        }catch(JwtException e){
            return false;
        }
    }
}
