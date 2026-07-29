package com.STUDENT_MANAGEMENT._SYSTEM.AUTHENTICATION.security.refresh;

import com.STUDENT_MANAGEMENT._SYSTEM.USER.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Long> {
    Optional<RefreshToken> findByToken(String token);

    @Modifying
    void deleteByUser(User user);
}
