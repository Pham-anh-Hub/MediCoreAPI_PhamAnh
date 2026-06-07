package org.example.medicoreapi.dto.response;

/**
 * ===================================================================
 * DTO: ApiResponse<T> (Cấu trúc phản hồi chung cho toàn hệ thống)
 * NGƯỜI LÀM: Nhóm trưởng tạo, CẢ NHÓM dùng chung
 * ===================================================================
 *
 * HƯỚNG DẪN:
 * - Generic wrapper cho mọi API response để đồng bộ format
 * - Dùng @Builder
 *
 * CÁC TRƯỜNG:
 * - success (boolean)
 * - message (String)
 * - data (T) - dữ liệu trả về, generic type
 * - timestamp (LocalDateTime)
 *
 * VÍ DỤ SỬ DỤNG:
 *   return ResponseEntity.ok(ApiResponse.builder()
 *       .success(true)
 *       .message("Lấy danh sách thành công")
 *       .data(doctorList)
 *       .build());
 */
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    private int status;
    private String message;
    private T data;
}