package com.kingoma.repositories;

import com.kingoma.entities.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
    void deleteByDoctor_Id(Long doctorId);
}
