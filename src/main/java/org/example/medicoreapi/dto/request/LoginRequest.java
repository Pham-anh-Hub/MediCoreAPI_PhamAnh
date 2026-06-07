package org.example.medicoreapi.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ===================================================================
 * DTO: LoginRequest
 * NGƯỜI LÀM: Người 1 - Phạm Phương Anh (Auth + JWT)
 * ===================================================================
 *
 * HƯỚNG DẪN:
 * - DTO nhận dữ liệu đăng nhập từ client
 * - Dùng @Valid + @NotBlank cho validation
 *
 * CÁC TRƯỜNG:
 * - username (String, @NotBlank)
 * - password (String, @NotBlank)
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LoginRequest {
    @NotBlank(message = "")
    private String username;
    @NotBlank(message = "")
    private String password;
}