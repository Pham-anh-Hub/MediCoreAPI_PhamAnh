package org.example.medicoreapi.config;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.Customizer;

import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * ===================================================================
 * CONFIG: SecurityConfig (Cấu hình Spring Security)
 * NGƯỜI LÀM: Người 2 - Lê Tiến Đức (Security + User/Admin)
 * ===================================================================
 *
 * HƯỚNG DẪN:
 * - @Configuration, @EnableWebSecurity, @EnableMethodSecurity
 * - Inject JwtAuthenticationFilter, UserService (UserDetailsService)
 *
 * CÁC BEAN CẦN KHAI BÁO:
 *
 * 1. SecurityFilterChain filterChain(HttpSecurity http)
 *    - Disable CSRF (vì dùng JWT, không dùng session)
 *    - Session: STATELESS
 *    - Cấu hình authorizeHttpRequests:
 *      + permitAll: /api/auth/login, /api/auth/refresh
 *      + Còn lại: authenticated
 *    - Thêm JwtAuthenticationFilter trước UsernamePasswordAuthenticationFilter
 *    - Xử lý exception: authenticationEntryPoint trả 401
 *
 * 2. PasswordEncoder passwordEncoder()
 *    - return new BCryptPasswordEncoder();
 *
 * 3. AuthenticationManager authenticationManager(AuthenticationConfiguration config)
 *    - return config.getAuthenticationManager();
 *
 * 4. AuthenticationProvider authenticationProvider()
 *    - DaoAuthenticationProvider, set UserDetailsService và PasswordEncoder
 *
 * LƯU Ý:
 * - Phối hợp Người 1 (Anh) về JwtAuthenticationFilter
 * - @EnableMethodSecurity để @PreAuthorize hoạt động
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authentication -> authentication
                        .requestMatchers("/api/auth/**").permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint((request, response, authException) -> {
                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                            response.setContentType("application/json");
                            response.getWriter().write("{\"error\": \"Unauthorized\", \"message\": \"" + authException.getMessage() + "\"}");
                        })
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
