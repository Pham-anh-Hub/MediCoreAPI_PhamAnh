package org.example.medicoreapi.service;

/**
 * ===================================================================
 * SERVICE: PatientService (Nghiệp vụ Bệnh nhân)
 * NGƯỜI LÀM: Người 4 - Phùng Văn Vượng (Patient + Booking)
 * ===================================================================
 *
 * HƯỚNG DẪN:
 * - @Service, inject PatientRepository, AppointmentRepository
 *
 * CÁC METHOD CẦN TRIỂN KHAI:
 * - PatientResponse createPatient(PatientRequest request)
 * - List<PatientResponse> getAllPatients() - dùng Stream API
 * - PatientResponse getPatientById(Long id)
 * - PatientResponse updatePatient(Long id, PatientRequest request)
 * - void deletePatient(Long id)
 *
 * - PatientResponse getMyProfile(Long patientUserId)
 *   + Bệnh nhân xem hồ sơ của chính mình
 *
 * - AppointmentResponse bookAppointment(Long patientUserId, AppointmentRequest request)
 *   + Tạo lịch hẹn mới, set status = PENDING
 *   + Kiểm tra trùng lịch (cùng bác sĩ, cùng ngày, cùng giờ)
 *
 * - List<AppointmentResponse> getMyAppointments(Long patientUserId)
 *   + Lấy danh sách lịch hẹn của bệnh nhân
 *   + Dùng Stream API để map entity -> response DTO
 *
 * LƯU Ý: Chỉ cho xem/đặt lịch của chính mình (kiểm tra userId)
 */
