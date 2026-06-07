package org.example.medicoreapi.repository;

import org.example.medicoreapi.entity.TokenBlacklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * ===================================================================
 * REPOSITORY: TokenBlacklistRepository
 * NGƯỜI LÀM: Người 1 - Phạm Phương Anh (Auth + JWT)
 * ===================================================================
 *
 * HƯỚNG DẪN:
 * - Extends JpaRepository<TokenBlacklist, Long>
 * - Query methods:
 *   + boolean existsByToken(String token)
 *   + void deleteByExpiryDateBefore(LocalDateTime dateTime) - dọn token hết hạn
 */

@Repository
public interface TokenBlacklistRepository extends JpaRepository<TokenBlacklist, Long> {
    boolean existsByToken(String token);
}