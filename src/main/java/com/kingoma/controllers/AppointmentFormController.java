package com.kingoma.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppointmentFormController {

    @GetMapping("/addAppointmentForm")
    public String showAddAppointmentForm() {
        return "Appointment/addAppointmentForm"; // Corresponds to the file name in templates (addAppointmentForm.html)
    }

    @GetMapping("/updateAppointmentForm")
    public String showUpdateAppointmentForm() {
        return "Appointment/updateAppointmentForm"; // Corresponds to the file name in templates (updateAppointmentForm.html)
    }
}
