package org.example.medicoreapi.controller;

/**
 * ===================================================================
 * TEST: AuthControllerTest
 * NGƯỜI LÀM: Người 1 - Phạm Phương Anh (Auth + JWT)
 * ===================================================================
 *
 * HƯỚNG DẪN:
 * - Dùng @WebMvcTest(AuthController.class)
 * - @MockBean AuthService, JwtService, UserDetailsService
 * - Inject MockMvc
 *
 * CÁC TEST CASE GỢI Ý:
 * - login_Success_Returns200()
 * - login_InvalidCredentials_Returns401()
 * - refreshToken_ValidRefreshToken_Returns200()
 * - logout_Success_Returns200()
 *
 * LƯU Ý:
 * - Dùng mockMvc.perform(post("/api/auth/login")...)
 * - Dùng .contentType(MediaType.APPLICATION_JSON)
 * - Dùng ObjectMapper để chuyển object sang JSON
 */
