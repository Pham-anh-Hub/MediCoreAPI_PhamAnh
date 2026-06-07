package org.example.medicoreapi.controller;

/**
 * ===================================================================
 * CONTROLLER: DoctorController (API Bác sĩ)
 * NGƯỜI LÀM: Người 3 - Lê Duy Minh (Doctor + Appointment)
 * ===================================================================
 *
 * HƯỚNG DẪN:
 * - @RestController, @RequestMapping("/api/doctors")
 * - Inject DoctorService
 *
 * CÁC ENDPOINT:
 *
 * === CRUD (ADMIN only) ===
 * POST   /api/doctors         - Tạo bác sĩ (@PreAuthorize ADMIN)
 * GET    /api/doctors         - Danh sách bác sĩ (@PreAuthorize ADMIN)
 * GET    /api/doctors/{id}    - Chi tiết bác sĩ (@PreAuthorize ADMIN)
 * PUT    /api/doctors/{id}    - Cập nhật (@PreAuthorize ADMIN)
 * DELETE /api/doctors/{id}    - Xóa (@PreAuthorize ADMIN)
 *
 * === Lịch khám của bác sĩ (DOCTOR only) ===
 * GET /api/doctors/my-appointments
 * - @PreAuthorize("hasRole('DOCTOR')")
 * - Lấy userId từ SecurityContextHolder -> Authentication
 * - Trả về lịch hẹn của bác sĩ đang đăng nhập
 *
 * GET /api/doctors/my-appointments/today
 * - @PreAuthorize("hasRole('DOCTOR')")
 * - Lọc lịch hẹn hôm nay
 *
 * LƯU Ý:
 * - Lấy user hiện tại: SecurityContextHolder.getContext().getAuthentication()
 * - Chặn xem lịch bác sĩ khác trong service layer
 */
