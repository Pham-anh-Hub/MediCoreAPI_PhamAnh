package org.example.medicoreapi.exception;

/**
 * ===================================================================
 * EXCEPTION: TokenExpiredException
 * NGƯỜI LÀM: Người 1 - Phạm Phương Anh (Auth + JWT)
 * ===================================================================
 *
 * HƯỚNG DẪN:
 * - Extends RuntimeException
 * - Dùng khi token JWT hết hạn
 * - GlobalExceptionHandler bắt exception này trả 401 + message gợi ý refresh
 */
public class TokenExpiredException extends RuntimeException {

    public TokenExpiredException(String message) {
        super(message);
    }
}