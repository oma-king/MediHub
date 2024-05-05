package com.kingoma.controllers;

import com.kingoma.dtos.ConsultationFormData;
import com.kingoma.entities.Consultation;
import com.kingoma.entities.Doctor;
import com.kingoma.entities.MedicalFile;
import com.kingoma.repositories.ConsultationRepository;
import com.kingoma.repositories.PrescriptionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ConsultationController {

    private final ConsultationRepository consultationRepository;
    private final PrescriptionRepository prescriptionRepository;
    public ConsultationController(ConsultationRepository consultationRepository, PrescriptionRepository prescriptionRepository) {
        this.consultationRepository = consultationRepository;
        this.prescriptionRepository = prescriptionRepository;
    }

    @GetMapping("/consultations")
    public String getAllConsultations(Model model) {
        List<Consultation> consultations = consultationRepository.findAll();
        model.addAttribute("consultations", consultations);
        return "Consultation/consultationIndex"; // Corresponds to the file name in templates (consultationIndex.html)
    }
    @GetMapping("/getConsultationById/{id}")
    public ResponseEntity<Consultation> getConsultationById(@PathVariable Long id) {
        Optional<Consultation> consultationOptional = consultationRepository.findById(id);
        if (consultationOptional.isPresent()) {
            Consultation consultation = consultationOptional.get();
            return ResponseEntity.ok().body(consultation);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/addConsultation")
    public ResponseEntity<String> addConsultation(@RequestBody ConsultationFormData formData) {
        if (formData != null) {

            Consultation consultation = new Consultation();
            // Set consultation properties from formData
            consultation.setReason(formData.getReason());
            consultation.setDescription(formData.getDescription());
            consultation.setConsultationDate(formData.getConsultationDate());
            consultation.setConsultationTime(formData.getConsultationTime());
            consultation.setPrice(formData.getPrice());
            // Create a new MedicalFile instance with the constructed content
            MedicalFile medicalFile = new MedicalFile();
            medicalFile.setMedicalFileId(formData.getMedicalFileId());
            consultation.setMedicalFile(medicalFile);
            // Create a new Doctor instance with the constructed content
            Doctor doctor = new Doctor();
            doctor.setId(formData.getDoctorId());
            consultation.setDoctor(doctor);
            // Save the Consultation entity
            consultationRepository.save(consultation);

            // Return a response entity with a success message
            return ResponseEntity.status(HttpStatus.CREATED).body("Consultation added successfully!");
        } else {
            // Return a response entity with an error message if formData is null
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to add consultation. Invalid form data.");
        }
    }

    @PutMapping("/updateConsultation/{id}")
    public ResponseEntity<String> updateConsultation(@PathVariable("id") Long id, @RequestBody ConsultationFormData formData) {
        Optional<Consultation> optionalConsultation = consultationRepository.findById(id);

        if (optionalConsultation.isPresent() && formData != null) {
            Consultation consultation = optionalConsultation.get();

            // Update consultation properties from formData
            consultation.setReason(formData.getReason());
            consultation.setDescription(formData.getDescription());
            consultation.setPrice(formData.getPrice());

            // Save the updated consultation entity
            consultationRepository.save(consultation);

            // Return a response entity with a success message
            return ResponseEntity.status(HttpStatus.OK).body("Consultation updated successfully!");
        } else {
            // Return a response entity with an error message if consultation not found or form data is invalid
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Failed to update consultation. Consultation not found or invalid form data.");
        }
    }


    @DeleteMapping("/deleteConsultation/{id}")
    @Transactional
    public ResponseEntity<String> deleteConsultation(@PathVariable Long id) {
        // Implement logic to delete the consultation with the provided ID

        // Check if the patient exists
        Optional<Consultation> optionalConsultation = consultationRepository.findById(id);
        if (optionalConsultation.isPresent()) {
            // consultation exists, delete it
            Consultation consultation = optionalConsultation.get();
            // Delete the corresponding prescriptions
            prescriptionRepository.deleteByConsultation_ConsultationId(id);
            // Delete the Consultation record
            consultationRepository.delete(consultation);

            return ResponseEntity.status(HttpStatus.OK).body("Consultation deleted successfully!");
        } else {
            // Consultation does not exist
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Failed to delete consultation. Consultation not found or invalid form data.");
        }
    }
}
