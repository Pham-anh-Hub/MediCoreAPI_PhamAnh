package org.example.medicoreapi.repository;

import org.example.medicoreapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * ===================================================================
 * REPOSITORY: UserRepository
 * NGƯỜI LÀM: Người 2 - Lê Tiến Đức (Security + User/Admin)
 * ===================================================================
 * <p>
 * HƯỚNG DẪN:
 * - Extends JpaRepository<User, Long>
 * - Thêm các query method cần thiết:
 * + Optional<User> findByUsername(String username)
 * + boolean existsByUsername(String username)
 * - Người 1 (Anh) cũng sẽ dùng repo này trong AuthService
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByUsername(String username);
}
