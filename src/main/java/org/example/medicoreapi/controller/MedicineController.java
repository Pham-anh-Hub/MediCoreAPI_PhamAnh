package org.example.medicoreapi.controller;

/**
 * ===================================================================
 * CONTROLLER: MedicineController (API Thuốc)
 * NGƯỜI LÀM: Người 5 - Trần Đăng Việt (Medicine + Prescription)
 * ===================================================================
 *
 * HƯỚNG DẪN:
 * - @RestController, @RequestMapping("/api/medicines")
 * - Inject MedicineService
 *
 * CÁC ENDPOINT:
 *
 * POST   /api/medicines         - Tạo thuốc (@PreAuthorize ADMIN)
 * GET    /api/medicines         - Danh sách thuốc (@PreAuthorize ADMIN hoặc DOCTOR)
 * GET    /api/medicines/{id}    - Chi tiết thuốc
 * PUT    /api/medicines/{id}    - Cập nhật (@PreAuthorize ADMIN)
 * DELETE /api/medicines/{id}    - Xóa (@PreAuthorize ADMIN)
 */
