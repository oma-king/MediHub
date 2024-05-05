package com.kingoma.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ConsultationFormController {

    @GetMapping("/addConsultationForm")
    public String showAddConsultationForm() {
        return "Consultation/addConsultationForm"; // Corresponds to the file name in templates (addConsultationForm.html)
    }

    @GetMapping("/updateConsultationForm")
    public String showUpdateConsultationForm() {
        return "Consultation/updateConsultationForm"; // Corresponds to the file name in templates (updateConsultationForm.html)
    }
}
