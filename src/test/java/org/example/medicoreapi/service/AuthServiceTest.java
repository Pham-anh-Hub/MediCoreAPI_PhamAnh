package org.example.medicoreapi.service;

/**
 * ===================================================================
 * TEST: AuthServiceTest
 * NGƯỜI LÀM: Người 1 - Phạm Phương Anh (Auth + JWT)
 * ===================================================================
 *
 * HƯỚNG DẪN:
 * - Dùng @ExtendWith(MockitoExtension.class)
 * - @Mock các dependency: UserRepository, JwtService, AuthenticationManager,
 *   TokenBlacklistRepository, PasswordEncoder
 * - @InjectMocks AuthService
 *
 * CÁC TEST CASE GỢI Ý:
 * - login_Success() - đăng nhập đúng username/password
 * - login_InvalidCredentials_ThrowsException()
 * - refreshToken_ValidToken_ReturnsNewAccessToken()
 * - refreshToken_BlacklistedToken_ThrowsException()
 * - logout_Success_TokenAddedToBlacklist()
 * - revokeAllUserTokens_Success()
 *
 * LƯU Ý:
 * - Dùng when(...).thenReturn(...) để mock
 * - Dùng verify(...) để kiểm tra method được gọi
 * - Dùng assertThrows() cho trường hợp lỗi
 */
