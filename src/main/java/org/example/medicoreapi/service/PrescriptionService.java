package org.example.medicoreapi.service;

/**
 * ===================================================================
 * SERVICE: PrescriptionService (Nghiệp vụ Đơn thuốc)
 * NGƯỜI LÀM: Người 5 - Trần Đăng Việt (Medicine + Prescription)
 * ===================================================================
 *
 * HƯỚNG DẪN:
 * - @Service, inject PrescriptionRepository, PrescriptionDetailRepository,
 *   MedicineRepository, AppointmentRepository, DoctorRepository, PatientRepository
 *
 * CÁC METHOD CẦN TRIỂN KHAI:
 * - PrescriptionResponse createPrescription(Long doctorUserId, PrescriptionRequest request)
 *   + Kiểm tra appointment tồn tại và thuộc về bác sĩ này
 *   + Tạo Prescription + danh sách PrescriptionDetail
 *   + Dùng Stream API: request.getItems().stream().map(...) để tạo detail list
 *   + Cập nhật appointment status = COMPLETED
 *
 * - PrescriptionResponse getPrescriptionById(Long id)
 *   + Chi tiết đơn thuốc kèm danh sách thuốc
 *
 * - List<PrescriptionResponse> getPatientPrescriptions(Long patientUserId)
 *   + Lịch sử đơn thuốc của bệnh nhân
 *   + Dùng Stream API
 *
 * LƯU Ý:
 * - Chỉ Doctor mới được tạo đơn thuốc
 * - Patient chỉ xem đơn thuốc của chính mình
 */
