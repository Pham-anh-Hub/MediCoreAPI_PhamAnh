package org.example.medicoreapi.repository;

/**
 * ===================================================================
 * REPOSITORY: PrescriptionRepository
 * NGƯỜI LÀM: Người 5 - Trần Đăng Việt (Medicine + Prescription)
 * ===================================================================
 *
 * HƯỚNG DẪN:
 * - Extends JpaRepository<Prescription, Long>
 * - Query methods:
 *   + List<Prescription> findByPatientId(Long patientId)
 *   + List<Prescription> findByDoctorId(Long doctorId)
 *   + Optional<Prescription> findByAppointmentId(Long appointmentId)
 */
