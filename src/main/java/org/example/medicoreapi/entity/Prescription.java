package org.example.medicoreapi.entity;

/**
 * ===================================================================
 * ENTITY: Prescription (Đơn thuốc)
 * NGƯỜI LÀM: Người 5 - Trần Đăng Việt (Medicine + Prescription)
 * ===================================================================
 *
 * HƯỚNG DẪN:
 * - Entity lưu thông tin đơn thuốc do bác sĩ kê sau khi khám
 * - @Entity, @Table(name = "prescriptions")
 *
 * CÁC TRƯỜNG CẦN CÓ:
 * - id (Long, @GeneratedValue)
 * - diagnosis (String) - chuẩn đoán bệnh
 * - notes (String) - ghi chú bác sĩ
 * - createdAt (LocalDateTime)
 *
 * QUAN HỆ:
 * - @ManyToOne với Doctor (bác sĩ kê đơn)
 * - @ManyToOne với Patient (bệnh nhân được kê)
 * - @OneToOne với Appointment (từ cuộc hẹn nào)
 * - @OneToMany với PrescriptionDetail (chi tiết thuốc)
 *
 * LƯU Ý:
 * - Dùng Lombok: @Data, @Builder, @NoArgsConstructor, @AllArgsConstructor
 */
