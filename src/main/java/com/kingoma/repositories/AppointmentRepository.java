package com.kingoma.repositories;

import com.kingoma.entities.Appointment;
import com.kingoma.entities.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByPatient_IdOrDoctor_IdOrNurse_Id(Long patientId, Long doctorId, Long nurseId);

}
