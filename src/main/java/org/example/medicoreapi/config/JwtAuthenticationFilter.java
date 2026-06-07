package org.example.medicoreapi.config;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.parser.Authorization;
import org.example.medicoreapi.entity.User;
import org.example.medicoreapi.exception.GlobalExceptionHandler;
import org.example.medicoreapi.exception.TokenExpiredException;
import org.example.medicoreapi.repository.UserRepository;
import org.example.medicoreapi.service.JwtService;
import org.example.medicoreapi.service.TokenBlacklistService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

/**
 * ===================================================================
 * FILTER: JwtAuthenticationFilter (Lọc và xác thực JWT mỗi request)
 * NGƯỜI LÀM: Người 1 - Phạm Phương Anh (Auth + JWT)
 * ===================================================================
 * <p>
 * HƯỚNG DẪN:
 * - Extends OncePerRequestFilter
 * - @Component
 * - Inject JwtService, UserDetailsService, TokenBlacklistRepository
 * <p>
 * LOGIC TRONG doFilterInternal():
 * 1. Lấy header "Authorization" từ request
 * 2. Nếu không có hoặc không bắt đầu bằng "Bearer " -> bỏ qua, gọi filterChain.doFilter()
 * 3. Extract token (bỏ "Bearer ")
 * 4. Kiểm tra token có trong blacklist không -> nếu có, trả 401
 * 5. Extract username từ token bằng JwtService
 * 6. Load UserDetails từ UserDetailsService
 * 7. Validate token (chữ ký, hết hạn, username khớp)
 * 8. Nếu hợp lệ -> tạo UsernamePasswordAuthenticationToken, set vào SecurityContextHolder
 * 9. Gọi filterChain.doFilter()
 * <p>
 * LƯU Ý:
 * - Bắt TokenExpiredException -> để GlobalExceptionHandler xử lý trả 401
 * - LOG: logger.warn() khi token bị blacklist hoặc hết hạn
 */

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final TokenBlacklistService tokenBlacklistService;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;
    private final GlobalExceptionHandler globalExceptionHandler;
    @Qualifier("handlerExceptionResolver")
    private final HandlerExceptionResolver handlerExceptionResolver;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // * 1. Lấy header "Authorization" từ request
        String authorization = request.getHeader("Authorization");

        // * 2. Nếu không có hoặc không bắt đầu bằng "Bearer "
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return; // -> bỏ qua, gọi filterChain.doFilter()
        }

        // * 3. Extract token (bỏ "Bearer ")
        String token = authorization.substring(7);

        try {
            // * 4. Kiểm tra token có trong blacklist không -> nếu có, trả 401
            // (Giả định bạn đã inject tokenBlacklistService ở tầng class)
            if (tokenBlacklistService.isBlacklisted(token)) {
                logger.warn("JWT Token bị từ chối: Token nằm trong blacklist.");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json");
                response.getWriter().write("{\"error\": \"Unauthorized\", \"message\": \"Token has been blacklisted.\"}");
                return;
            }

            // * 5. Extract username từ token bằng JwtService
            String username = jwtService.extractUsername(token);

            // Kiểm tra nếu có username và SecurityContext chưa được thiết lập authentication trước đó
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                // * 6. Load UserDetails từ UserDetailsService
                User user = userRepository.findUserByUsername(username).orElse(null);
                UserDetails targetUserDetail = userDetailsService.loadUserByUsername(username);

                // * 7. Validate token (chữ ký, hết hạn, username khớp)
                if (jwtService.isTokenValid(token, user)) {

                    // * 8. Nếu hợp lệ -> tạo UsernamePasswordAuthenticationToken, set vào SecurityContextHolder
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(targetUserDetail, null, targetUserDetail.getAuthorities());
                    // Thêm thông tin chi tiết của request (IP, Session...) vào authToken
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    // Lưu vào SecurityContextHolder để các filter sau hoặc Controller biết user đã log in
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }

            // * 9. Gọi filterChain.doFilter() cho các request hợp lệ đi tiếp
            filterChain.doFilter(request, response);

        } catch (TokenExpiredException ex) {
            // LOG: logger.warn() khi token bị hết hạn
            logger.warn("JWT Token bị từ chối: Token đã hết hạn. " + ex.getMessage());
            // 1. SỬA TẠI ĐÂY: Khởi tạo đúng Custom Exception của bạn
            TokenExpiredException customException = new TokenExpiredException("Token của bạn đã hết hạn. Vui lòng refresh token hoặc đăng nhập lại.");

            // 2. ĐÚNG ĐẮN: Đẩy CUSTOM exception này qua Resolver chứ không đẩy 'ex' nữa
            handlerExceptionResolver.resolveException(request, response, null, customException);
        }
    }
}
