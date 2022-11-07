package com.example.fms.festmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.fms.festmanagement.models.Participant;
import com.example.fms.festmanagement.models.User;
import com.example.fms.festmanagement.service.AuthenticationService;
import com.example.fms.festmanagement.service.RegistrationService;

import jakarta.servlet.http.HttpSession;

@Controller
@Transactional
public class HomeController extends Helper{

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/")
    public String home(Model model, HttpSession session){
        if (authenticationService.isAuthenticated(session)) {
            addDefaultAttributes(model, session);
            System.out.println(model.getAttribute("userRole"));
            if(model.getAttribute("userRole").equals("participant")){
                return "redirect:dashboard";
            }
            else if(model.getAttribute("userRole").equals("organiser")){
                return "redirect:organiserdashboard";
            }
            else{
                return "redirect:admindashboard";
            }
        }
        return "home";
    }

    @GetMapping("/register")
    public String registration(Model model, HttpSession session, RedirectAttributes attributes) {
        if (authenticationService.isAuthenticated(session)) {
            addDefaultAttributes(model, session);
            System.out.println(model.getAttribute("userRole"));
            if(model.getAttribute("userRole").equals("participant")){
                return "redirect:dashboard";
            }
            else if(model.getAttribute("userRole").equals("organiser")){
                return "redirect:organiserdashboard";
            }
            else{
                return "redirect:admindashboard";
            }
        }
        addDefaultAttributes(model, session);

        model.addAttribute("users", new User());
        model.addAttribute("participant", new Participant());
        return  "register";
    }

    @PostMapping("/register")
    public String postRegistration(@ModelAttribute User users, @ModelAttribute Participant participant, Model model, HttpSession session, RedirectAttributes attributes) {
        System.out.println(users.toString());
        System.out.println(participant.toString());
        registrationService.register(users,participant);
        System.out.println("registered");
        return  "redirect:/";
    }

    @GetMapping("accessdenied")
    public String AccessDenied(HttpSession session, Model model){
        return "accessdenied";
    }

}
