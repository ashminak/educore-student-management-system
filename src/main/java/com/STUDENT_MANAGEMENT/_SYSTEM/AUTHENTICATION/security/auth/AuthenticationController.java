package com.STUDENT_MANAGEMENT._SYSTEM.AUTHENTICATION.security.auth;

import com.STUDENT_MANAGEMENT._SYSTEM.DTO.RequestDTO.LoginRequest;
import com.STUDENT_MANAGEMENT._SYSTEM.DTO.RequestDTO.RefreshTokenRequest;
import com.STUDENT_MANAGEMENT._SYSTEM.DTO.RequestDTO.RegisterRequest;
import com.STUDENT_MANAGEMENT._SYSTEM.DTO.ResponseDTO.ApiResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.DTO.ResponseDTO.LoginResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.DTO.ResponseDTO.RegisterResponse;
import com.STUDENT_MANAGEMENT._SYSTEM.AUTHENTICATION.security.user.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(
        name = "Authentication",
        description = "Authentication and authorization APIs"
)
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @Operation(
            summary = "Register User",
            description = "Register a new user."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "User registered successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Validation failed")
    })
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<RegisterResponse>> register(
                @Valid
                @RequestBody
                RegisterRequest registerRequest) {
        RegisterResponse registerResponse = authenticationService.register(registerRequest);
        ApiResponse<RegisterResponse> apiResponse = ApiResponse.<RegisterResponse>builder()
                .success(true)
                .message("Register successfully!")
                .data(registerResponse)
                .timestamp(LocalDateTime.now())
                .build();
        return  ResponseEntity
                .status(HttpStatus.CREATED)
                .body(apiResponse);
    }
    @Operation(
            summary = "Login",
            description = "Authenticate user and generate JWT access and refresh tokens."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Login successful"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Invalid credentials")
    })
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(
            @Valid
            @RequestBody
            LoginRequest loginRequest){
        LoginResponse loginResponse = authenticationService.login(loginRequest);
        ApiResponse<LoginResponse> apiResponse =
                ApiResponse.<LoginResponse>builder()
                        .success(true)
                        .message("Login")
                        .data(loginResponse)
                        .timestamp(LocalDateTime.now())
                        .build();
        return  ResponseEntity.ok(apiResponse);
    }
    @Operation(
            summary = "Refresh Access Token",
            description = "Generate a new access token using a valid refresh token."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Access token refreshed"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Invalid refresh token")
    })
    @PostMapping("/refresh-token")
    public ResponseEntity<ApiResponse<LoginResponse>> refreshToken(
            @Valid
            @RequestBody
            RefreshTokenRequest refreshTokenRequest){

        LoginResponse loginResponse =
                authenticationService.refreshToken(refreshTokenRequest.getRefreshToken());
        ApiResponse<LoginResponse> apiResponse =
                ApiResponse.<LoginResponse>builder()
                        .success(true)
                        .message("Access Token refreshed successfully")
                        .data(loginResponse)
                        .timestamp(LocalDateTime.now())
                        .build();
        return  ResponseEntity.ok(apiResponse);

    }
    @Operation(
            summary = "Current User",
            description = "Retrieve the currently authenticated user's details.",
            security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "User details retrieved"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER','STUDENT')")
    @GetMapping("/me")
    public ResponseEntity<ApiResponse<LoginResponse>> getCurrentUser(Authentication authentication){
        CustomUserDetails user =
                (CustomUserDetails) authentication.getPrincipal();
        LoginResponse response =
                LoginResponse.builder()
                        .user(
                                com.STUDENT_MANAGEMENT._SYSTEM.DTO.ResponseDTO.UserResponse.builder()
                                        .id(user.getId())
                                        .fullName(user.getFullName())
                                        .email(user.getEmail())
                                        .username(user.getUsername())
                                        .build()
                        )
                        .build();
        ApiResponse<LoginResponse> apiResponse =
                ApiResponse.<LoginResponse>builder()
                        .success(true)
                        .message("Current Authenticated User")
                        .data(response)
                        .timestamp(LocalDateTime.now())
                        .build();
        return ResponseEntity.ok(apiResponse);
    }

}
