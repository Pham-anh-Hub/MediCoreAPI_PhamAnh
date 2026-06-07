package org.example.medicoreapi.service;


import org.example.medicoreapi.entity.TokenBlacklist;
import org.example.medicoreapi.repository.TokenBlacklistRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TokenBlacklistService {

    private final TokenBlacklistRepository tokenBlacklistRepository;

    public TokenBlacklistService(TokenBlacklistRepository tokenBlacklistRepository) {
        this.tokenBlacklistRepository = tokenBlacklistRepository;
    }

    /**
     * Kiểm tra token có bị cho vào danh sách đen hay không
     */
    public boolean isBlacklisted(String token) {
        return tokenBlacklistRepository.existsByToken(token);
    }


    public void blacklistToken(String token, String tokenType, LocalDateTime expiryDate, String revokedBy ){
        if (!tokenBlacklistRepository.existsByToken(token)) {
            TokenBlacklist blacklistEntry = TokenBlacklist.builder()
                    .token(token)
                    .tokenType(tokenType) // "ACCESS" hoặc "REFRESH"
                    .expiryDate(expiryDate)
                    .revokedAt(LocalDateTime.now())
                    .revokedBy(revokedBy)
                    .build();

            tokenBlacklistRepository.save(blacklistEntry);
        }
    }
}