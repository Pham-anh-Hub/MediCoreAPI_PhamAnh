package org.example.medicoreapi.exception;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * ===================================================================
 * EXCEPTION: GlobalExceptionHandler (Xử lý ngoại lệ tập trung - AOP)
 * NGƯỜI LÀM: Nhóm trưởng tạo khung, MỖI NGƯỜI bổ sung exception của module mình
 * ===================================================================
 * <p>
 * HƯỚNG DẪN:
 * - @RestControllerAdvice (AOP - bắt exception toàn cục)
 * - Mỗi method dùng @ExceptionHandler để bắt exception cụ thể
 * <p>
 * CÁC EXCEPTION CẦN XỬ LÝ (KHUNG BAN ĐẦU):
 * <p>
 * 1. @ExceptionHandler(ResourceNotFoundException.class)
 * - Trả 404 NOT_FOUND + ApiResponse(success=false, message=...)
 * <p>
 * 2. @ExceptionHandler(BadRequestException.class)
 * - Trả 400 BAD_REQUEST
 * <p>
 * 3. @ExceptionHandler(AccessDeniedException.class)
 * - Trả 403 FORBIDDEN
 * <p>
 * 4. @ExceptionHandler(AuthenticationException.class)
 * - Trả 401 UNAUTHORIZED
 * <p>
 * 5. @ExceptionHandler(TokenExpiredException.class) - Người 1 bổ sung
 * - Trả 401 UNAUTHORIZED + message gợi ý dùng refresh token
 * <p>
 * 6. @ExceptionHandler(MethodArgumentNotValidException.class)
 * - Trả 400 + danh sách lỗi validation
 * <p>
 * 7. @ExceptionHandler(Exception.class) - fallback
 * - Trả 500 INTERNAL_SERVER_ERROR
 * - LOG: logger.error() ghi chi tiết lỗi
 * <p>
 * FORMAT TRẢ VỀ THỐNG NHẤT:
 * { "success": false, "message": "...", "data": null, "timestamp": "..." }
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<Map<String, Object>> handleTokenExpired(TokenExpiredException ex) {
        Map<String, Object> errors = new HashMap<>();
        errors.put("status", HttpStatus.UNAUTHORIZED.value());
        errors.put("error", "Unauthorized");

        // Trả về message gợi ý refresh token đúng theo yêu cầu
        errors.put("message", ex.getMessage());
        errors.put("timestamp", LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errors);
    }
}