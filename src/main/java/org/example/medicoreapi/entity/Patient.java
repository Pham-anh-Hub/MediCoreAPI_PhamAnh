package org.example.medicoreapi.entity;

/**
 * ===================================================================
 * ENTITY: Patient (Thông tin Bệnh nhân)
 * NGƯỜI LÀM: Người 4 - Phùng Văn Vượng (Patient + Booking)
 * ===================================================================
 *
 * HƯỚNG DẪN:
 * - Entity lưu thông tin chi tiết bệnh nhân
 * - @Entity, @Table(name = "patients")
 *
 * CÁC TRƯỜNG CẦN CÓ:
 * - id (Long, @GeneratedValue)
 * - fullName (String, not null)
 * - dateOfBirth (LocalDate)
 * - gender (String)
 * - phone (String)
 * - address (String)
 * - createdAt, updatedAt (LocalDateTime)
 *
 * QUAN HỆ:
 * - @OneToOne với User
 * - @OneToMany với Appointment
 * - @OneToMany với Prescription
 *
 * LƯU Ý:
 * - Dùng Lombok: @Data, @Builder, @NoArgsConstructor, @AllArgsConstructor
 * - Phối hợp với Người 2 (Đức) để thống nhất quan hệ User-Patient
 */
