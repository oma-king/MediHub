package com.kingoma.controllers;

import com.kingoma.dtos.DoctorFormData;
import com.kingoma.entities.Doctor;
import com.kingoma.repositories.DoctorRepository;
import com.kingoma.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Optional;

@Controller
public class HomeController {

    @GetMapping("/")
    public String showIndexPage(Model model) {
        return "Index";
    }
}
