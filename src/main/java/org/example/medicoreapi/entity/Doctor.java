package org.example.medicoreapi.entity;

/**
 * ===================================================================
 * ENTITY: Doctor (Thông tin Bác sĩ)
 * NGƯỜI LÀM: Người 3 - Lê Duy Minh (Doctor + Appointment)
 * ===================================================================
 *
 * HƯỚNG DẪN:
 * - Entity lưu thông tin chi tiết bác sĩ
 * - @Entity, @Table(name = "doctors")
 *
 * CÁC TRƯỜNG CẦN CÓ:
 * - id (Long, @GeneratedValue)
 * - fullName (String, not null)
 * - specialization (String) - chuyên khoa
 * - phone (String)
 * - email (String)
 * - createdAt, updatedAt (LocalDateTime)
 *
 * QUAN HỆ:
 * - @OneToOne với User (mappedBy hoặc @JoinColumn)
 * - @OneToMany với Appointment
 *
 * LƯU Ý:
 * - Dùng Lombok: @Data, @Builder, @NoArgsConstructor, @AllArgsConstructor
 * - Phối hợp với Người 2 (Đức) để thống nhất quan hệ User-Doctor
 */
