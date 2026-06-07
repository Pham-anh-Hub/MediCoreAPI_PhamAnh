package org.example.medicoreapi.entity;

/**
 * ===================================================================
 * ENTITY: PrescriptionDetail (Chi tiết đơn thuốc - thuốc nào, liều lượng)
 * NGƯỜI LÀM: Người 5 - Trần Đăng Việt (Medicine + Prescription)
 * ===================================================================
 *
 * HƯỚNG DẪN:
 * - Entity trung gian giữa Prescription và Medicine
 * - @Entity, @Table(name = "prescription_details")
 *
 * CÁC TRƯỜNG CẦN CÓ:
 * - id (Long, @GeneratedValue)
 * - quantity (Integer) - số lượng
 * - dosage (String) - liều dùng (VD: "2 viên/ngày, sau ăn")
 * - notes (String) - ghi chú thêm
 *
 * QUAN HỆ:
 * - @ManyToOne với Prescription
 * - @ManyToOne với Medicine
 *
 * LƯU Ý:
 * - Dùng Lombok: @Data, @Builder, @NoArgsConstructor, @AllArgsConstructor
 */
