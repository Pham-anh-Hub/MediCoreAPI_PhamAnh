package org.example.medicoreapi.dto.request;

/**
 * ===================================================================
 * DTO: AppointmentRequest
 * NGƯỜI LÀM: Người 4 - Phùng Văn Vượng (Patient + Booking)
 * ===================================================================
 *
 * HƯỚNG DẪN:
 * - DTO nhận dữ liệu khi bệnh nhân đặt lịch khám
 *
 * CÁC TRƯỜNG:
 * - doctorId (Long, @NotNull)
 * - appointmentDate (LocalDate, @NotNull, @FutureOrPresent)
 * - timeSlot (String, @NotBlank) - VD: "08:00", "09:30"
 * - notes (String) - ghi chú tùy chọn
 */
