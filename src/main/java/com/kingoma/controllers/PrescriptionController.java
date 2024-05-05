package com.kingoma.controllers;

import com.kingoma.dtos.PrescriptionFormData;
import com.kingoma.entities.Consultation;
import com.kingoma.entities.Prescription;
import com.kingoma.repositories.ConsultationRepository;
import com.kingoma.repositories.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class PrescriptionController {

    private final PrescriptionRepository prescriptionRepository;
    private final ConsultationRepository consultationRepository;


    public PrescriptionController(PrescriptionRepository prescriptionRepository, ConsultationRepository consultationRepository) {
        this.prescriptionRepository = prescriptionRepository;
        this.consultationRepository = consultationRepository;
    }
    @GetMapping("/prescriptions")
    public String getAllConsultations(Model model) {
        List<Prescription> prescriptions = prescriptionRepository.findAll();
        model.addAttribute("prescriptions", prescriptions);
        return "Prescription/prescriptionIndex"; // Corresponds to the file name in templates (prescriptionIndex.html)
    }
    @PostMapping("/addPrescription")
    public ResponseEntity<String> addPrescription(@RequestBody PrescriptionFormData formData) {
        if (formData != null) {
            // Fetch the consultation associated with the prescription
            Consultation consultation = consultationRepository.findById(formData.getConsultationId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid consultation ID"));

            // Create a new Prescription instance
            Prescription prescription = new Prescription();
            prescription.setTitle(formData.getTitle());
            prescription.setPrescriptionDate(formData.getPrescriptionDate());
            prescription.setDescription(formData.getDescription());
            prescription.setItemList(formData.getItemList());
            prescription.setConsultation(consultation);

            // Save the prescription
            prescriptionRepository.save(prescription);

            // Return a response entity with a success message
            return ResponseEntity.status(HttpStatus.CREATED).body("Prescription added successfully!");
        } else {
            // Return a response entity with an error message if formData is null
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to add prescription. Invalid form data.");
        }
    }

    @PutMapping("/updatePrescription/{id}")
    public ResponseEntity<String> updatePrescription(@PathVariable("id") Long id, @RequestBody PrescriptionFormData formData) {
        Optional<Prescription> prescriptionOptional = prescriptionRepository.findById(id);
        if (prescriptionOptional.isPresent()) {
            Prescription prescription = prescriptionOptional.get();
            prescription.setTitle(formData.getTitle());
            prescription.setDescription(formData.getDescription());
            prescription.setItemList(formData.getItemList());
            prescriptionRepository.save(prescription);
            return ResponseEntity.ok("Prescription updated successfully!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deletePrescription/{id}")
    public ResponseEntity<String> deletePrescription(@PathVariable("id") Long id) {
        Optional<Prescription> prescriptionOptional = prescriptionRepository.findById(id);
        if (prescriptionOptional.isPresent()) {
            prescriptionRepository.delete(prescriptionOptional.get());
            return ResponseEntity.ok("Prescription deleted successfully!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getPrescriptionById/{id}")
    public ResponseEntity<Prescription> getPrescriptionById(@PathVariable("id") Long id) {
        Optional<Prescription> prescriptionOptional = prescriptionRepository.findById(id);
        return prescriptionOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/getPrescriptionByConsultationId/{id}")
    public ResponseEntity<List<Prescription>> getPrescriptionsByConsultationId(@PathVariable("id") Long id) {
        // Find prescriptions by consultation ID
        List<Prescription> prescriptions = prescriptionRepository.findByConsultation_ConsultationId(id);

        // Check if prescriptions are found
        if (!prescriptions.isEmpty()) {
            return ResponseEntity.ok(prescriptions); // Return prescriptions if found
        } else {
            return ResponseEntity.notFound().build(); // Return 404 if no prescriptions found
        }
    }

}
