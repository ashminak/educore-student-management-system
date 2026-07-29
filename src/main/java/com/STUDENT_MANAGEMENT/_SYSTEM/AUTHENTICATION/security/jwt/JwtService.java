package com.STUDENT_MANAGEMENT._SYSTEM.AUTHENTICATION.security.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtService {
    private final JwtTokenProvider jwtTokenProvider;
    public String generateAccessToken(
            UserDetails user){
        return jwtTokenProvider.generateAccessToken(
                user.getUsername());
    }
    public String generateRefreshToken(
            UserDetails user){
        return jwtTokenProvider.generateRefreshToken(
                user.getUsername()
        );
    }
    public String extractUsername(String token){
        return jwtTokenProvider.extractUsername(token);
    }
    public boolean isValidToken(
            String token,
            UserDetails user
    ){
        return
                jwtTokenProvider.isTokenValid(token)
                &&
                user.getUsername()
                        .equals(
                                jwtTokenProvider.extractUsername(token)
                        );

    }

}
