package org.example.medicoreapi.exception;

/**
 * ===================================================================
 * EXCEPTION: ResourceNotFoundException
 * NGƯỜI LÀM: Nhóm trưởng tạo, CẢ NHÓM dùng chung
 * ===================================================================
 *
 * HƯỚNG DẪN:
 * - Extends RuntimeException
 * - Dùng khi không tìm thấy entity (Doctor, Patient, Medicine, User...)
 *
 * VÍ DỤ: throw new ResourceNotFoundException("Doctor not found with id: " + id);
 */
