package org.example.medicoreapi.service;

/**
 * ===================================================================
 * TEST: DoctorServiceTest
 * NGƯỜI LÀM: Người 3 - Lê Duy Minh (Doctor + Appointment)
 * ===================================================================
 *
 * HƯỚNG DẪN:
 * - @ExtendWith(MockitoExtension.class)
 * - @Mock DoctorRepository, AppointmentRepository
 * - @InjectMocks DoctorService
 *
 * CÁC TEST CASE GỢI Ý:
 * - createDoctor_Success()
 * - getAllDoctors_ReturnsList()
 * - getDoctorById_NotFound_ThrowsException()
 * - getMyAppointments_Success()
 * - getMyTodayAppointments_FiltersCorrectly()
 * - getMyAppointments_WrongDoctor_ThrowsAccessDenied()
 */
