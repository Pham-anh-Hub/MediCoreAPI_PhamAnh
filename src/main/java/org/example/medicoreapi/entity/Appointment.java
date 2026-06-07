package org.example.medicoreapi.entity;

/**
 * ===================================================================
 * ENTITY: Appointment (Lịch khám bệnh)
 * NGƯỜI LÀM: Người 3 - Lê Duy Minh + Người 4 - Phùng Văn Vượng (phối hợp)
 * CHỊU TRÁCH NHIỆM CHÍNH: Người 4 - Phùng Văn Vượng (tạo file)
 * ===================================================================
 *
 * HƯỚNG DẪN:
 * - Entity lưu thông tin lịch hẹn khám bệnh
 * - @Entity, @Table(name = "appointments")
 *
 * CÁC TRƯỜNG CẦN CÓ:
 * - id (Long, @GeneratedValue)
 * - appointmentDate (LocalDate)
 * - timeSlot (String hoặc LocalTime) - khung giờ khám
 * - status (Enum: PENDING, CONFIRMED, COMPLETED, CANCELLED)
 * - notes (String) - ghi chú
 * - createdAt (LocalDateTime)
 *
 * QUAN HỆ:
 * - @ManyToOne với Doctor
 * - @ManyToOne với Patient
 * - @OneToOne với Prescription (sau khi khám xong)
 *
 * LƯU Ý:
 * - Tạo enum AppointmentStatus trong package enums
 * - Phối hợp Người 3 (Minh) và Người 4 (Vượng) thống nhất cấu trúc
 */
