package org.example.medicoreapi.dto.response;

/**
 * ===================================================================
 * DTO: AuthResponse (Phản hồi đăng nhập / refresh token)
 * NGƯỜI LÀM: Người 1 - Phạm Phương Anh (Auth + JWT)
 * ===================================================================
 *
 * HƯỚNG DẪN:
 * - DTO trả về sau khi login hoặc refresh token thành công
 *
 * CÁC TRƯỜNG:
 * - accessToken (String)
 * - refreshToken (String)
 * - tokenType (String) - mặc định "Bearer"
 * - expiresIn (Long) - thời gian sống access token (giây)
 * - role (String) - vai trò user
 *
 * LƯU Ý: Dùng @Builder pattern cho dễ khởi tạo
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponse {

    private String accessToken;
    private String refreshToken;

    private String tokenType = "Bearer";
    private Long expiresIn; // Thời gian sống của access token (tính bằng giây)
    private String role;    // Vai trò của user (ví dụ: "ADMIN", "DOCTOR", "PATIENT")
}