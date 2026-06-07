package org.example.medicoreapi.entity;

/**
 * ===================================================================
 * ENTITY: Medicine (Danh mục Thuốc)
 * NGƯỜI LÀM: Người 5 - Trần Đăng Việt (Medicine + Prescription)
 * ===================================================================
 *
 * HƯỚNG DẪN:
 * - Entity lưu danh mục thuốc của hệ thống
 * - @Entity, @Table(name = "medicines")
 *
 * CÁC TRƯỜNG CẦN CÓ:
 * - id (Long, @GeneratedValue)
 * - name (String, not null)
 * - unit (String) - đơn vị (viên, gói, ống...)
 * - description (String)
 * - price (BigDecimal)
 * - createdAt, updatedAt (LocalDateTime)
 *
 * QUAN HỆ:
 * - @OneToMany với PrescriptionDetail
 *
 * LƯU Ý:
 * - Dùng Lombok: @Data, @Builder, @NoArgsConstructor, @AllArgsConstructor
 */
