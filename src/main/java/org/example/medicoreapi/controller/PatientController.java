package org.example.medicoreapi.controller;

/**
 * ===================================================================
 * CONTROLLER: PatientController (API Bệnh nhân + Đặt lịch)
 * NGƯỜI LÀM: Người 4 - Phùng Văn Vượng (Patient + Booking)
 * ===================================================================
 *
 * HƯỚNG DẪN:
 * - @RestController, @RequestMapping("/api/patients")
 * - Inject PatientService
 *
 * CÁC ENDPOINT:
 *
 * === CRUD (ADMIN only) ===
 * POST   /api/patients         - Tạo bệnh nhân (@PreAuthorize ADMIN)
 * GET    /api/patients         - Danh sách bệnh nhân (@PreAuthorize ADMIN)
 * GET    /api/patients/{id}    - Chi tiết (@PreAuthorize ADMIN)
 * PUT    /api/patients/{id}    - Cập nhật (@PreAuthorize ADMIN)
 * DELETE /api/patients/{id}    - Xóa (@PreAuthorize ADMIN)
 *
 * === Bệnh nhân tự xem hồ sơ (PATIENT only) ===
 * GET /api/patients/my-profile
 * - @PreAuthorize("hasRole('PATIENT')")
 * - Lấy userId từ SecurityContext, trả hồ sơ của mình
 *
 * === Đặt lịch khám (PATIENT only) ===
 * POST /api/patients/appointments
 * - @PreAuthorize("hasRole('PATIENT')")
 * - Nhận: @RequestBody @Valid AppointmentRequest
 * - Trả: 201 Created + AppointmentResponse
 *
 * GET /api/patients/my-appointments
 * - @PreAuthorize("hasRole('PATIENT')")
 * - Lấy danh sách lịch hẹn của bệnh nhân đang đăng nhập
 */
