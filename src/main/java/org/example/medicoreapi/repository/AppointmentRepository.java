package org.example.medicoreapi.repository;

/**
 * ===================================================================
 * REPOSITORY: AppointmentRepository
 * NGƯỜI LÀM: Người 4 - Phùng Văn Vượng (tạo), Người 3 - Lê Duy Minh (dùng chung)
 * ===================================================================
 *
 * HƯỚNG DẪN:
 * - Extends JpaRepository<Appointment, Long>
 * - Query methods cần thiết:
 *   + List<Appointment> findByDoctorId(Long doctorId)
 *   + List<Appointment> findByPatientId(Long patientId)
 *   + List<Appointment> findByDoctorIdAndAppointmentDate(Long doctorId, LocalDate date)
 *   + Sử dụng Stream API khi xử lý danh sách tại Service layer
 */
