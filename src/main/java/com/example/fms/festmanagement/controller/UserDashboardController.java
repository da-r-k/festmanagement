package com.example.fms.festmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

import com.example.fms.festmanagement.service.AuthenticationService;
import com.example.fms.festmanagement.service.DashboardService;

@Controller
public class UserDashboardController extends Helper {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session) {
        if (!isAuthenticated(session)) {
            return "redirect:/";
        }

        addDefaultAttributes(model, session);
        System.out.println(model.getAttribute("userRole"));
        if(model.getAttribute("userRole").equals("participant")){
            model.addAttribute("subEvents", dashboardService.getSubEventToday());
            model.addAttribute("correspondingevents", dashboardService.getEventFromSubEvent(dashboardService.getSubEventToday()));
            return "dashboard";
        }
        else if(model.getAttribute("userRole").equals("organiser")){
            return "redirect:organiserdashboard";
        }
        return "redirect:/";
    }
}
