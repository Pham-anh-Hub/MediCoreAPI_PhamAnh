package org.example.medicoreapi.service;

/**
 * ===================================================================
 * SERVICE: DoctorService (Nghiệp vụ Bác sĩ)
 * NGƯỜI LÀM: Người 3 - Lê Duy Minh (Doctor + Appointment)
 * ===================================================================
 *
 * HƯỚNG DẪN:
 * - @Service, inject DoctorRepository, AppointmentRepository
 *
 * CÁC METHOD CẦN TRIỂN KHAI:
 * - DoctorResponse createDoctor(DoctorRequest request)
 * - List<DoctorResponse> getAllDoctors() - dùng Stream API để map entity -> DTO
 * - DoctorResponse getDoctorById(Long id)
 * - DoctorResponse updateDoctor(Long id, DoctorRequest request)
 * - void deleteDoctor(Long id)
 *
 * - List<AppointmentResponse> getMyAppointments(Long doctorUserId)
 *   + Lấy doctor từ userId hiện tại (từ SecurityContext)
 *   + Trả về danh sách lịch hẹn của bác sĩ đó
 *   + Dùng Stream API: filter, map, collect
 *
 * - List<AppointmentResponse> getMyTodayAppointments(Long doctorUserId)
 *   + Lọc lịch hẹn theo ngày hôm nay (LocalDate.now())
 *
 * LƯU Ý:
 * - Chặn xem lịch bác sĩ khác: kiểm tra doctorId có khớp user đang đăng nhập không
 * - Ném AccessDeniedException nếu không khớp
 */
