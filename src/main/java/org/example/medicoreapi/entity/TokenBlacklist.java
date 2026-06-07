package org.example.medicoreapi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * ===================================================================
 * ENTITY: TokenBlacklist (Danh sách đen token đã bị thu hồi)
 * NGƯỜI LÀM: Người 1 - Phạm Phương Anh (Auth + JWT)
 * ===================================================================
 *
 * HƯỚNG DẪN:
 * - Entity lưu các token đã bị revoke (logout hoặc admin khóa)
 * - @Entity,
 *
 * CÁC TRƯỜNG CẦN CÓ:
 * - id (Long, @GeneratedValue)
 * - token (String, columnDefinition = "TEXT") - chuỗi JWT token
 * - tokenType (String) - "ACCESS" hoặc "REFRESH"
 * - expiryDate (LocalDateTime) - ngày hết hạn token (dùng để dọn dẹp DB)
 * - revokedAt (LocalDateTime) - thời điểm bị thu hồi
 * - revokedBy (String) - ai thu hồi (user tự logout hay admin)
 *
 * LƯU Ý:
 * - Có thể thêm @Index trên cột token để tăng tốc truy vấn kiểm tra blacklist
 * - Dùng Lombok: @Data, @Builder, @NoArgsConstructor, @AllArgsConstructor
 */

@Entity
@Table(name = "token_blacklist")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TokenBlacklist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    private String tokenType;
    private LocalDateTime expiryDate;
    private LocalDateTime revokedAt;
    private String revokedBy;
}
