package com.STUDENT_MANAGEMENT._SYSTEM.AUTHENTICATION.security.auth;

import com.STUDENT_MANAGEMENT._SYSTEM.DTO.RequestDTO.LoginRequest;
import com.STUDENT_MANAGEMENT._SYSTEM.DTO.RequestDTO.RegisterRequest;
import com.STUDENT_MANAGEMENT._SYSTEM.DTO.ResponseDTO.LoginResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.DTO.ResponseDTO.RegisterResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.DTO.ResponseDTO.UserResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.USER.entity.User;
import com.STUDENT_MANAGEMENT._SYSTEM.enums.Role;
import com.STUDENT_MANAGEMENT._SYSTEM.USER.repository.UserRepository;
import com.STUDENT_MANAGEMENT._SYSTEM.AUTHENTICATION.security.jwt.JwtService;
import com.STUDENT_MANAGEMENT._SYSTEM.AUTHENTICATION.security.refresh.RefreshTokenService;
import com.STUDENT_MANAGEMENT._SYSTEM.AUTHENTICATION.security.user.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;

    @Override
    public RegisterResponse register(RegisterRequest request) {
        log.info("Registering user {}",request.getUsername());
        if(userRepository.existsByUsername(request.getUsername())){
            throw new RuntimeException("Username is already Exists.");
        }
        if(userRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("Email is already Exists.");
        }
        User user = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .username(request.getUsername())
                .password(passwordEncoder.encode(
                        request.getPassword()
                ))
                .role(request.getRole())
                .enabled(true)
                .accountNumLocked(true)
                .build();
        userRepository.save(user);
        log.info("User {} has been registered successfully",
                user.getUsername());
        return RegisterResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .message("Registration Successful")
                .build();
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getUsername(),
                                request.getPassword()
                        )
                );
        CustomUserDetails userDetails =
                (CustomUserDetails) authentication.getPrincipal();
        String accessToken =
                jwtService.generateAccessToken(userDetails);
        String refreshToken =
                refreshTokenService
                        .createRefreshToken(userDetails.getUsername());
        User user =
                userRepository
                        .findByUsername(userDetails.getUsername())
                        .orElseThrow();
        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .tokenType("Bearer")
                .expiresIn(900000L)
                .user(
                   UserResponse.builder()
                           .id(userDetails.getId())
                           .fullName(userDetails.getFullName())
                           .email(userDetails.getEmail())
                           .username(userDetails.getUsername())
                           .role(userDetails.getRole())
                           .build()
                )
                .build();


    }

    @Override
    public LoginResponse refreshToken(String refreshToken) {
       String username =
               refreshTokenService.validateRefreshToken(refreshToken);
       CustomUserDetails userDetails =
               (CustomUserDetails)
               authenticationManager.authenticate(
                       new UsernamePasswordAuthenticationToken(
                               username,
                               null
                       )
               )
                       .getPrincipal();
       String accessToken =
               jwtService.generateAccessToken(userDetails);
       return LoginResponse.builder()
               .accessToken(accessToken)
               .refreshToken(refreshToken)
               .tokenType("Bearer")
               .expiresIn(900000L)
               .build();

    }
}
