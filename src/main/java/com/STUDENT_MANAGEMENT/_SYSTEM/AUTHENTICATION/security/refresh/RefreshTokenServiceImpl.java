package com.STUDENT_MANAGEMENT._SYSTEM.AUTHENTICATION.security.refresh;

import com.STUDENT_MANAGEMENT._SYSTEM.USER.entity.User;
import com.STUDENT_MANAGEMENT._SYSTEM.USER.repository.UserRepository;
import com.STUDENT_MANAGEMENT._SYSTEM.AUTHENTICATION.security.config.JwtProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class RefreshTokenServiceImpl implements RefreshTokenService {
    private final UserRepository userRepository;
    private final RefreshTokenRepository repository;
    private final JwtProperties jwtProperties;
    @Override
    public String createRefreshToken(String username) {
        User user =
                userRepository.findByUsername(username).orElseThrow();


        repository.deleteByUser(user);
        String token = UUID.randomUUID().toString();
        RefreshToken refreshToken =
                RefreshToken.builder()
                        .token(token)
                        .expiryDate(
                                LocalDateTime.now()
                                        .plusSeconds(
                                                jwtProperties.getRefreshExpiration()/1000
                                        )

                        )
                        .revoked(false)

                        .user(user)
                .build();
        repository.save(refreshToken);
        return token;
    }

    @Override
    public String validateRefreshToken(String token) {
            RefreshToken refreshToken =
                    repository.findByToken(token)
                            .orElseThrow(()->
                                    new RuntimeException("Refresh token not found"));
            if(refreshToken.getRevoked()){
               throw new RuntimeException("Refresh token is revoked");
            }
            if (refreshToken.getExpiryDate().isBefore(LocalDateTime.now())) {
                throw new RuntimeException("Refresh token is expired");
            }
            return refreshToken.getUser().getUsername();
    }

    @Override
    public void revokeUserToken(Long userId) {
        User user = userRepository
                .findById(userId)
                .orElseThrow();
        repository.deleteByUser(user);
    }
}
