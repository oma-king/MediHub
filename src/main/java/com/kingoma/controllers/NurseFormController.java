package com.kingoma.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NurseFormController {

    @GetMapping("/addNurseForm")
    public String showAddNurseForm() {
        return "User/Nurse/addNurseForm"; // This corresponds to the file name in templates (addNurseForm.html)
    }
    @GetMapping("/updateNurseForm")
    public String showUpdateNurseForm() {
        return "User/Nurse/updateNurseForm"; // This corresponds to the file name in templates (updateNurseForm.html)
    }
}