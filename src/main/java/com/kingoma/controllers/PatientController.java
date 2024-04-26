package com.kingoma.controllers;

import com.kingoma.dtos.PatientFormData;
import com.kingoma.entities.Patient;
import com.kingoma.repositories.PatientRepository;
import com.kingoma.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PatientController {

    private final UserRepository userRepository;
    private final PatientRepository patientRepository;


    public PatientController(UserRepository userRepository, PatientRepository patientRepository) {
        this.userRepository = userRepository;
        this.patientRepository = patientRepository;
    }

    @GetMapping("/")
    public String showIndexPage(Model model) {
        // Get all patients directly from the repository
        model.addAttribute("patients", patientRepository.findAll());
        return "index";
    }
    @PostMapping("/addPatient")
    @ResponseBody
    public String addPatient(@RequestBody PatientFormData formData) {
        // Create a new Patient instance
        Patient patient = new Patient(
                formData.getEmail(),
                formData.getPassword(),
                formData.getBirthdate(),
                formData.getSocialSecurityType()
        );

        // Save the Patient entity
        patientRepository.save(patient);

        return "Patient added successfully!";
    }
}
