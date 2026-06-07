package org.example.medicoreapi.dto.request;

/**
 * ===================================================================
 * DTO: PrescriptionRequest
 * NGƯỜI LÀM: Người 5 - Trần Đăng Việt (Medicine + Prescription)
 * ===================================================================
 *
 * HƯỚNG DẪN:
 * - DTO nhận dữ liệu khi bác sĩ tạo đơn thuốc
 *
 * CÁC TRƯỜNG:
 * - appointmentId (Long, @NotNull) - từ cuộc hẹn nào
 * - diagnosis (String, @NotBlank) - chuẩn đoán
 * - notes (String)
 * - items (List<PrescriptionItemRequest>) - danh sách thuốc kê
 *
 * LƯU Ý:
 * - Tạo thêm inner class hoặc class riêng PrescriptionItemRequest gồm:
 *   + medicineId (Long)
 *   + quantity (Integer)
 *   + dosage (String) - liều dùng
 */
