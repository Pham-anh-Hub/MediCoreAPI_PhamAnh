package org.example.medicoreapi.controller;

/**
 * ===================================================================
 * CONTROLLER: AuthController (API xác thực: login, refresh, logout)
 * NGƯỜI LÀM: Người 1 - Phạm Phương Anh (Auth + JWT)
 * ===================================================================
 *
 * HƯỚNG DẪN:
 * - @RestController, @RequestMapping("/api/auth")
 * - Inject AuthService
 *
 * CÁC ENDPOINT:
 *
 * POST /api/auth/login
 * - Nhận: @RequestBody @Valid LoginRequest
 * - Trả: ResponseEntity<ApiResponse<AuthResponse>> (200 OK)
 *
 * POST /api/auth/refresh
 * - Nhận: @RequestBody @Valid RefreshTokenRequest
 * - Trả: ResponseEntity<ApiResponse<AuthResponse>> (200 OK)
 * - Logic: gửi refresh token, nhận access token mới
 *
 * POST /api/auth/logout
 * - Nhận: Access token từ Header "Authorization: Bearer xxx"
 *         Refresh token từ @RequestBody
 * - Trả: ResponseEntity<ApiResponse<Void>> (200 OK)
 * - Logic: đưa cả 2 token vào blacklist
 *
 * LƯU Ý:
 * - Endpoint login và refresh KHÔNG cần authentication (permitAll)
 * - Endpoint logout CẦN authentication
 * - Phối hợp Người 2 (Đức) để cấu hình permitAll trong SecurityConfig
 */


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.medicoreapi.dto.request.LoginRequest;
import org.example.medicoreapi.dto.request.LogoutRequest;
import org.example.medicoreapi.dto.request.RefreshTokenRequest;
import org.example.medicoreapi.dto.response.ApiResponse;
import org.example.medicoreapi.dto.response.AuthResponse;
import org.example.medicoreapi.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // Inject AuthService qua Constructor thuần để tránh lỗi xung đột của Lombok

    /**
     * POST /api/auth/login
     * Đăng nhập hệ thống, cấp cặp token mới
     */
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@RequestBody @Valid LoginRequest request) {
        AuthResponse authResponse = authService.login(request);

        ApiResponse<AuthResponse> response = ApiResponse.<AuthResponse>builder()
                .status(HttpStatus.OK.value())
                .message("Đăng nhập thành công.")
                .data(authResponse)
                .build();

        return ResponseEntity.ok(response);
    }

    /**
     * POST /api/auth/refresh
     * Gửi refresh token hợp lệ để đổi lấy access token mới
     */
    @PostMapping("/refresh")
    public ResponseEntity<ApiResponse<AuthResponse>> refresh(@RequestBody @Valid RefreshTokenRequest request) {
        AuthResponse authResponse = authService.refreshToken(request.getRefreshToken());

        ApiResponse<AuthResponse> response = ApiResponse.<AuthResponse>builder()
                .status(HttpStatus.OK.value())
                .message("Làm mới token thành công.")
                .data(authResponse)
                .build();

        return ResponseEntity.ok(response);
    }

    /**
     * POST /api/auth/logout
     * Đăng xuất: Đưa cả Access Token (Header) và Refresh Token (Body) vào Blacklist
     */
    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Void>> logout(
            @RequestHeader("Authorization") String authHeader,
            @RequestBody @Valid LogoutRequest request) {

        // Trích xuất Access Token từ Header bằng cách bỏ chuỗi "Bearer "
        String accessToken = null;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            accessToken = authHeader.substring(7);
        }

        // Gọi nghiệp vụ xử lý vô hiệu hóa cả 2 token tại AuthService
        authService.logout(accessToken, request.getRefreshToken());

        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .status(HttpStatus.OK.value())
                .message("Đăng xuất thành công. Các token đã bị hủy bỏ.")
                .data(null)
                .build();

        return ResponseEntity.ok(response);
    }
}