package org.example.medicoreapi.enums;

/**
 * ===================================================================
 * ENUM: Role (Vai trò người dùng)
 * NGƯỜI LÀM: Người 2 - Lê Tiến Đức (Security + User/Admin)
 * ===================================================================
 *
 * HƯỚNG DẪN:
 * - Định nghĩa 3 vai trò: ADMIN, DOCTOR, PATIENT
 * - Dùng trong entity User và JWT claims
 */
public enum Role {
    ADMIN,
    DOCTOR,
    PATIENT
}