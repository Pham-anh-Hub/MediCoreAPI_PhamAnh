package org.example.medicoreapi.service;

/**
 * ===================================================================
 * SERVICE: MedicineService (Nghiệp vụ Thuốc)
 * NGƯỜI LÀM: Người 5 - Trần Đăng Việt (Medicine + Prescription)
 * ===================================================================
 *
 * HƯỚNG DẪN:
 * - @Service, inject MedicineRepository
 *
 * CÁC METHOD CẦN TRIỂN KHAI:
 * - MedicineResponse createMedicine(MedicineRequest request)
 * - List<MedicineResponse> getAllMedicines() - dùng Stream API
 * - MedicineResponse getMedicineById(Long id)
 * - MedicineResponse updateMedicine(Long id, MedicineRequest request)
 * - void deleteMedicine(Long id)
 */
