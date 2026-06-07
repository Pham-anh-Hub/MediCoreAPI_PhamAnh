package org.example.medicoreapi.dto.request;

/**
 * ===================================================================
 * DTO: RegisterRequest
 * NGƯỜI LÀM: Người 2 - Lê Tiến Đức (Security + User/Admin)
 * ===================================================================
 *
 * HƯỚNG DẪN:
 * - DTO nhận dữ liệu tạo tài khoản mới
 *
 * CÁC TRƯỜNG:
 * - username (String, @NotBlank)
 * - password (String, @NotBlank, @Size(min=6))
 * - role (String hoặc Role enum)
 * - fullName (String)
 * - Các trường bổ sung tùy role (phone, email, specialization...)
 */
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RegisterRequest {
    @NotBlank(message = "Username không được trống")
    private String username;
    @NotBlank(message = "Password không được trống")
    private String password;
    @NotBlank(message = "Role không được trống")
    private String role; // Ví dụ: "ADMIN", "DOCTOR"
}