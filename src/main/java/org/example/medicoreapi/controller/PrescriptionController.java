package org.example.medicoreapi.controller;

/**
 * ===================================================================
 * CONTROLLER: PrescriptionController (API Đơn thuốc)
 * NGƯỜI LÀM: Người 5 - Trần Đăng Việt (Medicine + Prescription)
 * ===================================================================
 *
 * HƯỚNG DẪN:
 * - @RestController, @RequestMapping("/api/prescriptions")
 * - Inject PrescriptionService
 *
 * CÁC ENDPOINT:
 *
 * POST /api/prescriptions
 * - @PreAuthorize("hasRole('DOCTOR')")
 * - Bác sĩ tạo đơn thuốc sau khi khám
 * - Nhận: @RequestBody @Valid PrescriptionRequest
 * - Trả: 201 Created + PrescriptionResponse
 *
 * GET /api/prescriptions/{id}
 * - @PreAuthorize("hasAnyRole('DOCTOR','PATIENT')")
 * - Chi tiết đơn thuốc (bao gồm danh sách thuốc)
 *
 * GET /api/prescriptions/my-prescriptions
 * - @PreAuthorize("hasRole('PATIENT')")
 * - Lịch sử đơn thuốc của bệnh nhân đang đăng nhập
 */
