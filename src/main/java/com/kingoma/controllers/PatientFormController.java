package com.kingoma.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PatientFormController {

    @GetMapping("/addPatientForm")
    public String showAddPatientForm() {
        return "addPatientForm"; // This corresponds to the file name in templates (addPatientForm.html)
    }
}
