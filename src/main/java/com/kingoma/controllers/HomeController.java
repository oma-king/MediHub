package com.kingoma.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    @PreAuthorize("hasRole('ROLE_PATIENT')")
    @GetMapping("/")
    public String showIndexPage(Model model) {
        return "Index";
    }
}
