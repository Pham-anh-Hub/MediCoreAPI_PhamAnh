package org.example.medicoreapi.service;

/**
 * ===================================================================
 * SERVICE: UserService (Quản lý tài khoản - CRUD + khóa/mở)
 * NGƯỜI LÀM: Người 2 - Lê Tiến Đức (Security + User/Admin)
 * ===================================================================
 *
 * HƯỚNG DẪN:
 * - @Service, inject UserRepository, PasswordEncoder, AuthService (để revoke token)
 *
 * CÁC METHOD CẦN TRIỂN KHAI:
 * - User createUser(RegisterRequest request) - Admin tạo tài khoản
 *   + Mã hóa password bằng BCryptPasswordEncoder
 *   + Gán role, lưu vào DB
 *
 * - List<User> getAllUsers() - lấy danh sách (dùng Stream API map sang DTO)
 * - User getUserById(Long id)
 * - User updateUser(Long id, RegisterRequest request)
 * - void deleteUser(Long id)
 *
 * - void lockAccount(Long userId) - Admin khóa tài khoản
 *   + Set enabled = false
 *   + Gọi AuthService.revokeAllUserTokens(userId) để thu hồi token
 *
 * - void unlockAccount(Long userId) - Admin mở khóa
 *
 * - UserDetails loadUserByUsername(String username)
 *   + Implement UserDetailsService interface
 *   + Trả về User entity (đã implement UserDetails)
 *
 * LOG: logger.info() khi tạo/khóa/mở tài khoản thành công
 */


import org.example.medicoreapi.dto.request.RegisterRequest;
import org.example.medicoreapi.dto.response.UserResponse;
import org.example.medicoreapi.entity.User;
import org.example.medicoreapi.enums.Role;
import org.example.medicoreapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService { // <-- Khớp mảnh ghép UserDetailsService luôn

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthService authService;
    private final UserDetailsService userDetailsService;


    /**
     * Admin tạo tài khoản mới
     */
    public User createUser(RegisterRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        // Mã hóa mật khẩu bằng BCrypt
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        // Gán Role (Chuyển chuỗi String từ Request thành Enum)
        user.setRole(Role.valueOf(request.getRole().toUpperCase()));
        user.setEnabled(true);

        User savedUser = userRepository.save(user);
        logger.info("Tạo tài khoản thành công cho người dùng '{}'", savedUser.getUsername());
        return savedUser;
    }

    /**
     * Lấy danh sách toàn bộ người dùng bằng Stream API map sang DTO
     */
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> UserResponse.builder()
                        .id(user.getId())
                        .username(user.getUsername())
                        .role(user.getRole().name())
                        .enabled(user.isEnabled())
                        .build())
                .collect(Collectors.toList());
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng có ID: " + id));
    }

    @Transactional
    public User updateUser(Long id, RegisterRequest request) {
        User user = getUserById(id);
        user.setUsername(request.getUsername());
        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }
        user.setRole(Role.valueOf(request.getRole().toUpperCase()));
        return userRepository.save(user);
    }

    @Transactional
    public void deleteUser(Long id) {
        User user = getUserById(id);
        userRepository.delete(user);
        logger.info("Đã xóa người dùng có ID: {}", id);
    }

    /**
     * Admin khóa tài khoản người dùng
     */
    @Transactional
    public void lockAccount(Long userId) {
        User user = getUserById(userId);
        user.setEnabled(false); // Set enabled = false
        userRepository.save(user);

        // Gọi sang AuthService để kick hết token cũ ra, buộc logout
        authService.revokeAllUserTokens(userId);

        logger.info("Đã KHÓA tài khoản thành công cho người dùng '{}'", user.getUsername());
    }

    /**
     * Admin mở khóa tài khoản
     */
    @Transactional
    public void unlockAccount(Long userId) {
        User user = getUserById(userId);
        user.setEnabled(true); // Set enabled = true
        userRepository.save(user);

        logger.info("Đã MỞ KHÓA tài khoản thành công cho người dùng '{}'", user.getUsername());
    }
}