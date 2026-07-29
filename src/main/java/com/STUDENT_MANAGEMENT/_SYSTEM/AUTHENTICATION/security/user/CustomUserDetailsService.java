package com.STUDENT_MANAGEMENT._SYSTEM.AUTHENTICATION.security.user;

import com.STUDENT_MANAGEMENT._SYSTEM.USER.entity.User;
import com.STUDENT_MANAGEMENT._SYSTEM.USER.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Loading user {}",username);
        User user = userRepository
                .findByUsername(username)
                .orElseThrow(() -> {
                    log.error("user{} not found", username);
                    return new UsernameNotFoundException(username);
                });
        return new CustomUserDetails(user);
    }
}
