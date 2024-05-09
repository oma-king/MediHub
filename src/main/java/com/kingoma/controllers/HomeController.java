package com.kingoma.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    @GetMapping("/")
    public String showIndexPage(Model model) {
        return "Index";
    }
    /*public String showIndexPage(Model model) {
        return "authentication-login";
    }*/
}
