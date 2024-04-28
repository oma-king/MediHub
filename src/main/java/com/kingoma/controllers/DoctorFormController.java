package com.kingoma.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DoctorFormController {

    @GetMapping("/addDoctorForm")
    public String showAddDoctorForm() {
        return "User/Doctor/addDoctorForm"; // This corresponds to the file name in templates (addDoctorForm.html)
    }
    @GetMapping("/updateDoctorForm")
    public String showUpdateDoctorForm() {
        return "User/Doctor/updateDoctorForm"; // This corresponds to the file name in templates (updateDoctorForm.html)
    }
}