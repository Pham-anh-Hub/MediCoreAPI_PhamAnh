package org.example.medicoreapi.config;

/**
 * ===================================================================
 * CONFIG: DataInitializer (Khởi tạo dữ liệu mẫu khi chạy lần đầu)
 * NGƯỜI LÀM: Người 2 - Lê Tiến Đức (Security + User/Admin)
 * ===================================================================
 *
 * HƯỚNG DẪN:
 * - Implement CommandLineRunner hoặc dùng @PostConstruct
 * - @Component
 * - Inject UserRepository, PasswordEncoder
 *
 * LOGIC:
 * - Kiểm tra nếu chưa có user nào trong DB -> tạo tài khoản ADMIN mặc định
 * - VD: username="admin", password=passwordEncoder.encode("admin123"), role=ADMIN
 *
 * LƯU Ý: Chỉ tạo khi DB rỗng, tránh tạo trùng mỗi lần restart
 */
