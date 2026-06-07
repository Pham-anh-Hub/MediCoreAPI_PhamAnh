package org.example.medicoreapi.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ===================================================================
 * DTO: RefreshTokenRequest
 * NGƯỜI LÀM: Người 1 - Phạm Phương Anh (Auth + JWT)
 * ===================================================================
 *
 * HƯỚNG DẪN:
 * - DTO nhận refresh token từ client để cấp access token mới
 *
 * CÁC TRƯỜNG:
 * - refreshToken (String, @NotBlank)
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RefreshTokenRequest {
    @NotBlank(message = "")
    private String refreshToken;
}