package com.kingoma.controllers;

import com.kingoma.dtos.AppointmentFormData;
import com.kingoma.entities.*;
import com.kingoma.repositories.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class AppointmentController {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final NurseRepository nurseRepository;
    private final MedicalFileRepository medicalFileRepository;

    public AppointmentController(AppointmentRepository appointmentRepository,
                                     PatientRepository patientRepository,
                                     DoctorRepository doctorRepository,
                                     NurseRepository nurseRepository,
                                     MedicalFileRepository medicalFileRepository) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.nurseRepository = nurseRepository;
        this.medicalFileRepository = medicalFileRepository;
    }
    @GetMapping("/appointments")
    public String getAllAppointments(Model model) {
        List<Appointment> appointments = appointmentRepository.findAll();
        model.addAttribute("appointments", appointments);
        return "Appointment/appointmentIndex"; // Corresponds to the file name in templates (prescriptionIndex.html)
    }
    @PostMapping("/addAppointment")
    public ResponseEntity<String> addAppointment(@RequestBody AppointmentFormData formData) {
        if (formData != null) {
            // Fetch related entities
            Patient patient = patientRepository.findById(formData.getPatientId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid patient ID"));

            Doctor doctor = doctorRepository.findById(formData.getDoctorId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid doctor ID"));

            Nurse nurse = nurseRepository.findById(formData.getNurseId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid nurse ID"));

            MedicalFile medicalFile = medicalFileRepository.findBymedicalFileId(formData.getMedicalFileId());

            // Create a new Appointment instance
            Appointment appointment = new Appointment();
            appointment.setAppointmentDate(formData.getAppointmentDate()); // Convert string to Date
            appointment.setAppointmentTime(formData.getAppointmentTime());
            appointment.setDescription(formData.getDescription());
            appointment.setSymptoms(formData.getSymptoms());
            appointment.setPriority(formData.getPriority());
            appointment.setPatient(patient);
            appointment.setDoctor(doctor);
            appointment.setNurse(nurse);
            appointment.setMedicalFile(medicalFile);

            // Save the appointment
            appointmentRepository.save(appointment);

            // Return a response entity with a success message
            return ResponseEntity.status(HttpStatus.CREATED).body("Appointment added successfully!");
        } else {
            // Return a response entity with an error message if formData is null
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to add appointment. Invalid form data.");
        }
    }

    @PutMapping("/updateAppointment/{id}")
    public ResponseEntity<String> updateAppointment(@PathVariable("id") Long id, @RequestBody AppointmentFormData formData) {
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(id);
        if (appointmentOptional.isPresent()) {
            Appointment appointment = appointmentOptional.get();
            // Update appointment fields
            appointment.setAppointmentDate(formData.getAppointmentDate());
            appointment.setAppointmentTime(formData.getAppointmentTime());
            appointment.setDescription(formData.getDescription());
            appointment.setSymptoms(formData.getSymptoms());
            appointment.setPriority(formData.getPriority());
            // Save the updated appointment
            appointmentRepository.save(appointment);
            return ResponseEntity.ok("Appointment updated successfully!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/deleteAppointment/{id}")
    public ResponseEntity<String> deleteAppointment(@PathVariable("id") Long id) {
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(id);
        if (appointmentOptional.isPresent()) {
            appointmentRepository.deleteById(id);
            return ResponseEntity.ok("Appointment deleted successfully!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/getAppointmentById/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable("id") Long id) {
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(id);
        return appointmentOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/appointments/{patientId}/{doctorId}/{nurseId}") //ignore one of the parameters by passing a placeholder value, such as -1 or null
    public ResponseEntity<List<Appointment>> getAppointmentsByPatientOrDoctorOrNurse(
            @PathVariable Long patientId,
            @PathVariable Long doctorId,
            @PathVariable Long nurseId) {

        List<Appointment> appointments = appointmentRepository.findByPatient_IdOrDoctor_IdOrNurse_Id(patientId, doctorId, nurseId);

        if (!appointments.isEmpty()) {
            return ResponseEntity.ok(appointments);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
