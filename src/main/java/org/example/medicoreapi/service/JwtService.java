package org.example.medicoreapi.service;

/**
 * ===================================================================
 * SERVICE: JwtService (Xử lý logic JWT token)
 * NGƯỜI LÀM: Người 1 - Phạm Phương Anh (Auth + JWT)
 * ===================================================================
 *
 * HƯỚNG DẪN:
 * - @Service class chứa toàn bộ logic tạo và xác thực JWT
 * - Inject @Value("${jwt.secret}"), @Value("${jwt.access-token-expiration}"),
 *   @Value("${jwt.refresh-token-expiration}")
 *
 * CÁC METHOD CẦN TRIỂN KHAI:
 * - String generateAccessToken(UserDetails userDetails) - tạo access token
 * - String generateRefreshToken(UserDetails userDetails) - tạo refresh token
 * - String extractUsername(String token) - lấy username từ token
 * - boolean isTokenValid(String token, UserDetails userDetails) - kiểm tra token hợp lệ
 * - boolean isTokenExpired(String token) - kiểm tra token hết hạn
 * - Claims extractAllClaims(String token) - private, parse token
 *
 * THƯ VIỆN DÙNG:
 * - io.jsonwebtoken.Jwts, Keys, Claims
 * - Đặt role vào claims: .claim("role", user.getRole().name())
 *
 * LOG: Dùng SLF4J logger.info() khi tạo token thành công
 */

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.example.medicoreapi.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Service
public class JwtService {
    private static final Logger logger = LoggerFactory.getLogger(JwtService.class);

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.access-token-expiration}")
    private long accessTokenExpiration; // Thời gian sống tính bằng mili-giây (ms)

    @Value("${jwt.refresh-token-expiration}")
    private long refreshTokenExpiration; // Thời gian sống tính bằng mili-giây (ms)

    /**
     * Tạo Access Token (Có chứa Claim Role)
     */
    public String generateAccessToken(User user) {
        String token = Jwts.builder()
                .setSubject(user.getUsername())
                .claim("role", user.getRole().name()) // Lấy trực tiếp từ Entity -->  an toàn
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + accessTokenExpiration))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();

        logger.info("Tạo thành công Access Token cho user '{}'", user.getUsername());
        return token;
    }

    /**
     * Tạo Refresh Token (Thường chỉ cần Subject để định danh, không cần mang Role)
     */
    public String generateRefreshToken(User user) {
        String token = Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + refreshTokenExpiration))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();

        logger.info("Tạo thành công Refresh Token cho user '{}'", user.getUsername());
        return token;
    }

    /**
     * Lấy username (Subject) từ token
     */
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    /**
     * Kiểm tra token hợp lệ (Trùng username và chưa hết hạn)
     */
    public boolean isTokenValid(String token, User user) {
        final String username = extractUsername(token);
        return (username.equals(user.getUsername()) && !isTokenExpired(token));
    }

    /**
     * Kiểm tra token đã hết hạn hay chưa
     */
    public boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    /**
     * Parse toàn bộ Claims từ Token (Private method)
     * Lưu ý: Đoạn code này viết theo cú pháp JJWT 0.11.x (rất phổ biến).
     * Nếu bạn dùng bản JJWT 0.12.x mới nhất, hãy đổi parserBuilder() thành parser() và getBody() thành getPayload().
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith((javax.crypto.SecretKey) getSigningKey()) // Ép kiểu sang SecretKey
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    /**
     * Chuyển đổi chuỗi Secret Key dạng thô thành mã khóa Key bảo mật
     */
    private Key getSigningKey() {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}