package org.example.medicoreapi.controller;

/**
 * ===================================================================
 * CONTROLLER: AdminController (API quản trị: quản lý user, khóa tài khoản)
 * NGƯỜI LÀM: Người 2 - Lê Tiến Đức (Security + User/Admin)
 * ===================================================================
 *
 * HƯỚNG DẪN:
 * - @RestController, @RequestMapping("/api/admin")
 * - Inject UserService, AuthService
 * - Tất cả endpoint đều cần @PreAuthorize("hasRole('ADMIN')")
 *
 * CÁC ENDPOINT:
 *
 * POST /api/admin/users - Tạo tài khoản mới
 * - Nhận: @RequestBody @Valid RegisterRequest
 * - Trả: 201 Created
 *
 * GET /api/admin/users - Danh sách tất cả tài khoản
 * - Trả: 200 OK + List
 *
 * GET /api/admin/users/{id} - Chi tiết 1 tài khoản
 * PUT /api/admin/users/{id} - Cập nhật tài khoản
 * DELETE /api/admin/users/{id} - Xóa tài khoản
 *
 * PUT /api/admin/users/{id}/lock - Khóa tài khoản + revoke token
 * PUT /api/admin/users/{id}/unlock - Mở khóa tài khoản
 *
 * POST /api/admin/users/{id}/revoke - Ép đăng xuất (revoke tất cả token)
 */
