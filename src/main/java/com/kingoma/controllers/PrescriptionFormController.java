package com.kingoma.controllers;

import com.kingoma.dtos.PrescriptionFormData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PrescriptionFormController {

    @GetMapping("/addPrescriptionForm")
    public String showAddPrescriptionForm() {
        return "Prescription/addPrescriptionForm"; // Corresponds to the file name in templates (addPrescriptionForm.html)
    }

    @GetMapping("/updatePrescriptionForm")
    public String showUpdatePrescriptionForm() {
        return "Prescription/updatePrescriptionForm"; // Corresponds to the file name in templates (updatePrescriptionForm.html)
    }
    @GetMapping("/getPrescriptionByConsultationIdForm")
    public String showGetPrescriptionByConsultationId() {
        return "Prescription/prescriptionByConsultationIndex"; // Corresponds to the file name in templates (updatePrescriptionForm.html)
    }

    // Add other mappings and methods as needed
}
