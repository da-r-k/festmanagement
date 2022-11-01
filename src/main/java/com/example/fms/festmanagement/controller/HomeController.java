package com.example.fms.festmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Transactional
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        return "home";
    }

}
