package com.STUDENT_MANAGEMENT._SYSTEM.AUTHENTICATION.security.auth;

import com.STUDENT_MANAGEMENT._SYSTEM.DTO.RequestDTO.LoginRequest;
import com.STUDENT_MANAGEMENT._SYSTEM.DTO.RequestDTO.RegisterRequest;
import com.STUDENT_MANAGEMENT._SYSTEM.DTO.ResponseDTO.LoginResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.DTO.ResponseDTO.RegisterResponse;

public interface AuthenticationService {

    RegisterResponse register(RegisterRequest request);

    LoginResponse login(LoginRequest request);

    LoginResponse refreshToken(String refreshToken);

}
