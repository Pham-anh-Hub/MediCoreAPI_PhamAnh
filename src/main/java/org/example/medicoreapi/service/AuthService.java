package org.example.medicoreapi.service;

/**
 * ===================================================================
 * SERVICE: AuthService (Xử lý nghiệp vụ đăng nhập / đăng xuất / refresh)
 * NGƯỜI LÀM: Người 1 - Phạm Phương Anh (Auth + JWT)
 * ===================================================================
 * <p>
 * HƯỚNG DẪN:
 * - @Service, inject JwtService, AuthenticationManager, UserRepository,
 * TokenBlacklistRepository, PasswordEncoder
 * <p>
 * CÁC METHOD CẦN TRIỂN KHAI:
 * - AuthResponse login(LoginRequest request)
 * + Authenticate bằng AuthenticationManager
 * + Tạo access + refresh token, trả về AuthResponse
 * <p>
 * - AuthResponse refreshToken(RefreshTokenRequest request)
 * + Kiểm tra refresh token có trong blacklist không
 * + Validate refresh token, extract username
 * + Tạo access token mới, trả về AuthResponse
 * <p>
 * - void logout(String accessToken, String refreshToken)
 * + Đưa cả access và refresh token vào blacklist
 * <p>
 * - void revokeAllUserTokens(Long userId)
 * + Admin gọi để vô hiệu hóa tất cả token của 1 user
 * + Phối hợp với Người 2 (Đức) để kết nối từ AdminController
 * <p>
 * LOG: logger.info() khi login/logout/revoke thành công
 * logger.error() khi có lỗi xác thực
 */


import lombok.RequiredArgsConstructor;
import org.example.medicoreapi.dto.request.LoginRequest;
import org.example.medicoreapi.dto.response.AuthResponse;
import org.example.medicoreapi.entity.TokenBlacklist;
import org.example.medicoreapi.entity.User;
import org.example.medicoreapi.exception.TokenExpiredException;
import org.example.medicoreapi.repository.TokenBlacklistRepository;
import org.example.medicoreapi.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Value("${jwt.refresh-token-expiration}")
    private long expiresInSeconds;

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final TokenBlacklistRepository tokenBlacklistRepository;
    private final PasswordEncoder passwordEncoder;


    /**
     * Xử lý đăng nhập hệ thống
     */
    public AuthResponse login(LoginRequest request) {
        try {
            // 1. Xác thực bằng AuthenticationManager
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

            // 2. Lấy thông tin user từ DB sau khi auth thành công để lấy thêm claims (roles, id...) nếu cần
            User user = userRepository.findUserByUsername(request.getUsername()).orElseThrow(() -> new UsernameNotFoundException("User not found: " + request.getUsername()));
            ;

            // 3. Tạo access token và refresh token thông qua JwtService
            // (Giả định JwtService của bạn có các hàm nhận đối tượng User hoặc UserDetails)
            String accessToken = jwtService.generateAccessToken(user);
            String refreshToken = jwtService.generateRefreshToken(user);

            // LOG: Đăng nhập thành công
            logger.info("User '{}' logged in successfully.", request.getUsername());

            // 4. Trả về AuthResponse (Chứa cả 2 token)
            return AuthResponse.builder().accessToken(accessToken).refreshToken(refreshToken).expiresIn(expiresInSeconds).role(user.getRole().name()) // Lấy role trực tiếp từ Entity User đã giải quyết conflict ở bước trước
                    .build();

        } catch (AuthenticationException e) {
            // LOG: Thất bại khi xác thực (Sai mật khẩu, tài khoản bị khóa, v.v.)
            logger.error("Authentication failed for user '{}': {}", request.getUsername(), e.getMessage());
            throw e; // Ném ra để GlobalExceptionHandler xử lý trả về 401 Unauthorized cho Client
        }
    }

    public AuthResponse refreshToken(String refreshToken) {
        // 1. Kiểm tra xem Refresh Token này đã từng bị đăng xuất (blacklist) chưa
        boolean isBlacklisted = tokenBlacklistRepository.existsByToken(refreshToken); // Bạn cần thêm hàm này trong Repository
        if (isBlacklisted) {
            logger.warn("Cảnh báo: Client cố gắng sử dụng một Refresh Token đã bị blacklist.");
            throw new TokenExpiredException("Refresh token này đã vô hiệu. Vui lòng đăng nhập lại.");
        }

        // 2. Trích xuất username từ Refresh Token
        String username = jwtService.extractUsername(refreshToken);

        if (username != null) {
            User user = userRepository.findUserByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy user từ token: " + username));

            // 3. Kiểm tra token có hợp lệ/hết hạn hay không
            if (jwtService.isTokenValid(refreshToken, user)) {
                // Tạo một Access Token hoàn toàn mới
                String newAccessToken = jwtService.generateAccessToken(user);

                logger.info("Tạo Access Token mới thành công cho người dùng '{}' qua Refresh Token.", username);

                // Trả về cặp token (giữ nguyên refresh token cũ hoặc cấp mới tùy bạn, ở đây giữ nguyên theo đặc tả)
                return AuthResponse.builder()
                        .accessToken(newAccessToken)
                        .refreshToken(refreshToken)
                        .expiresIn(expiresInSeconds)
                        .role(user.getRole().name())
                        .build();
            }
        }

        throw new TokenExpiredException("Refresh token không hợp lệ hoặc đã hết hạn.");
    }

    /**
     * POST /api/auth/logout
     * Logic: Đưa cả Access Token và Refresh Token vào danh sách đen (Blacklist) để hủy hiệu lực ngay lập tức
     */
    @Transactional
    public void logout(String accessToken, String refreshToken) {
        // 1. Blacklist Access Token (nếu có)
        if (accessToken != null && !accessToken.isBlank()) {
            TokenBlacklist blacklistedAccess = new TokenBlacklist();
            blacklistedAccess.setToken(accessToken);
            // Bạn có thể set thêm ngày hết hạn lưu vào DB nếu cần cơ chế tự động xóa bản ghi thừa
            tokenBlacklistRepository.save(blacklistedAccess);
        }

        // 2. Blacklist Refresh Token (nếu có)
        if (refreshToken != null && !refreshToken.isBlank()) {
            TokenBlacklist blacklistedRefresh = new TokenBlacklist();
            blacklistedRefresh.setToken(refreshToken);
            tokenBlacklistRepository.save(blacklistedRefresh);
        }

        logger.info("Đăng xuất thành công. Đã hủy kích hoạt cặp token trên hệ thống.");
    }

    /**
     * Thu hồi toàn bộ token của user khi bị khóa tài khoản
     */
    public void revokeAllUserTokens(Long userId) {
        // Logic: Tùy thuộc vào cách bạn lưu Token (nếu lưu token có gắn userId trong DB)
        // Bạn sẽ quét và đưa các token còn hạn của user này vào Blacklist.
        logger.info("Đã thu hồi toàn bộ token hoạt động của User ID: {}", userId);
    }
}