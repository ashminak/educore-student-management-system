package com.STUDENT_MANAGEMENT._SYSTEM.AUTHENTICATION.security.refresh;

import org.springframework.stereotype.Service;

@Service
public interface RefreshTokenService {
    String createRefreshToken(String username);
    String validateRefreshToken(String token);
    void revokeUserToken(Long userId);

}
