package com.example.fms.festmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.fms.festmanagement.models.Organiser;
import com.example.fms.festmanagement.models.User;
import com.example.fms.festmanagement.service.AuthenticationService;
import com.example.fms.festmanagement.service.DashboardService;
import com.example.fms.festmanagement.service.OrganiserDashboardService;
import com.example.fms.festmanagement.service.RegistrationService;

import jakarta.servlet.http.HttpSession;

@Controller
public class OrganiserDashboardController extends Helper{

    @Autowired
    private DashboardService dashboardService;

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private OrganiserDashboardService organiserDashboardService;
    
    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("organiserdashboard")
    public String OrganiserDashboard(Model model, HttpSession session, RedirectAttributes attributes){
        model.addAttribute("subevents", dashboardService.getSubEventToday());
        model.addAttribute("correspondingevents", dashboardService.getEventFromSubEvent(dashboardService.getSubEventToday()));
        return "organiserdashboard";
    }

    @GetMapping("addorganisers")
    public String AddOrganisers(Model model, HttpSession session, RedirectAttributes attributes){
        if (!isAuthenticated(session)) {
            return "redirect:/";
        }

        return "addorganisers";
    }

    @PostMapping("addorganisers")
    public String postAddOrganisers(@ModelAttribute User users, @ModelAttribute Organiser organiser, Model model, HttpSession session, RedirectAttributes attributes) {
        System.out.println(users.toString());
        System.out.println(organiser.toString());
        registrationService.addOrganisers(users,organiser);
        System.out.println("added");
        return  "redirect:/";
    }

    @GetMapping("vieworganisers")
    public String ViewOrganisers(Model model, HttpSession session){
        model.addAttribute("organisers", organiserDashboardService.getOrganisersByEvent(organiserDashboardService.getEventFromOrganiser(authenticationService.getCurrentUser(session))));
        return "vieworganisers";
    } 

}
