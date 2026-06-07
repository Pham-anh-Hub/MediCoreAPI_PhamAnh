package org.example.medicoreapi.service;

/**
 * ===================================================================
 * TEST: PrescriptionServiceTest
 * NGƯỜI LÀM: Người 5 - Trần Đăng Việt (Medicine + Prescription)
 * ===================================================================
 *
 * HƯỚNG DẪN:
 * - @ExtendWith(MockitoExtension.class)
 * - @Mock PrescriptionRepository, PrescriptionDetailRepository,
 *   MedicineRepository, AppointmentRepository
 * - @InjectMocks PrescriptionService
 *
 * CÁC TEST CASE GỢI Ý:
 * - createPrescription_Success()
 * - createPrescription_AppointmentNotFound_ThrowsException()
 * - getPrescriptionById_Success()
 * - getPatientPrescriptions_ReturnsList()
 */
