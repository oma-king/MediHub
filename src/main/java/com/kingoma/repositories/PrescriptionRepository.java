package com.kingoma.repositories;

import com.kingoma.entities.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
    List<Prescription> findByConsultation_ConsultationId(Long consultationId);
    void deleteByConsultation_ConsultationId(Long consultationId);
}