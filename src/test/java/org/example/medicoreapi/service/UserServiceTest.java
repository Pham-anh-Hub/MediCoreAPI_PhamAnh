package org.example.medicoreapi.service;

/**
 * ===================================================================
 * TEST: UserServiceTest
 * NGƯỜI LÀM: Người 2 - Lê Tiến Đức (Security + User/Admin)
 * ===================================================================
 *
 * HƯỚNG DẪN:
 * - @ExtendWith(MockitoExtension.class)
 * - @Mock UserRepository, PasswordEncoder, AuthService
 * - @InjectMocks UserService
 *
 * CÁC TEST CASE GỢI Ý:
 * - createUser_Success()
 * - createUser_DuplicateUsername_ThrowsException()
 * - getAllUsers_ReturnsList()
 * - lockAccount_Success_RevokesTokens()
 * - unlockAccount_Success()
 * - deleteUser_NotFound_ThrowsException()
 */
