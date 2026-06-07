package org.example.medicoreapi.entity;

/**
 * ===================================================================
 * ENTITY: User (Bảng tài khoản đăng nhập)
 * NGƯỜI LÀM: Người 2 - Lê Tiến Đức (Security + User/Admin)
 * ===================================================================
 *
 * HƯỚNG DẪN:
 * - Đây là entity chung cho tất cả tài khoản đăng nhập (Admin, Doctor, Patient)
 * - Sử dụng @Entity, @Table(name = "users")
 * - Implement UserDetails của Spring Security
 *
 * CÁC TRƯỜNG CẦN CÓ:
 * - id (Long, @GeneratedValue)
 * - username (String, unique, not null)
 * - password (String, not null) - lưu dạng BCrypt
 * - role (Enum: ADMIN, DOCTOR, PATIENT)
 * - enabled (boolean) - để khóa/mở tài khoản
 * - createdAt, updatedAt (LocalDateTime)
 *
 * QUAN HỆ:
 * - @OneToOne với Doctor (nếu role=DOCTOR)
 * - @OneToOne với Patient (nếu role=PATIENT)
 *
 * LƯU Ý:
 * - Tạo enum Role trong package enums
 * - Override các method của UserDetails (getAuthorities, isAccountNonLocked...)
 * - Dùng @Enumerated(EnumType.STRING) cho role
 * - Dùng Lombok: @Data, @Builder, @NoArgsConstructor, @AllArgsConstructor
 */

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.medicoreapi.enums.Role;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password; // Lưu dưới dạng BCrypt

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false)
    private boolean enabled = true; // Mặc định mở tài khoản khi tạo

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;


}
