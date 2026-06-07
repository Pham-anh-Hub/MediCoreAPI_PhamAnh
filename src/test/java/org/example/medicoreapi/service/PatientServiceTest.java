package org.example.medicoreapi.service;

/**
 * ===================================================================
 * TEST: PatientServiceTest
 * NGƯỜI LÀM: Người 4 - Phùng Văn Vượng (Patient + Booking)
 * ===================================================================
 *
 * HƯỚNG DẪN:
 * - @ExtendWith(MockitoExtension.class)
 * - @Mock PatientRepository, AppointmentRepository
 * - @InjectMocks PatientService
 *
 * CÁC TEST CASE GỢI Ý:
 * - createPatient_Success()
 * - getMyProfile_Success()
 * - bookAppointment_Success()
 * - bookAppointment_DuplicateSlot_ThrowsException()
 * - getMyAppointments_Success()
 * - getMyProfile_WrongPatient_ThrowsAccessDenied()
 */
