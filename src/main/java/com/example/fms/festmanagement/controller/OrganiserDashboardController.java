package com.example.fms.festmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.fms.festmanagement.models.Competition;
import com.example.fms.festmanagement.models.Organiser;
import com.example.fms.festmanagement.models.SubEvent;
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
        return  "redirect:/vieworganisers";
    }

    @GetMapping("vieworganisers")
    public String ViewOrganisers(Model model, HttpSession session){
        model.addAttribute("organisers", organiserDashboardService.getOrganisersByEvent(organiserDashboardService.getEventFromOrganiser(authenticationService.getCurrentUser(session))));
        return "vieworganisers";
    } 

    @GetMapping("viewsubevents")
    public String ViewSubEvents(Model model,HttpSession session){
        model.addAttribute("subevents",organiserDashboardService.getSubEvents((organiserDashboardService.getEventFromOrganiser(authenticationService.getCurrentUser(session)))));
        return "viewsubevents";
    }

    @GetMapping("addsubevent")
    public String AddSubEvents(Model model, HttpSession session, RedirectAttributes attributes){
        model.addAttribute("subevent",new SubEvent());
        model.addAttribute("event",organiserDashboardService.getEventFromOrganiser(authenticationService.getCurrentUser(session)));
        return "addsubevent";
    }

    @PostMapping("addsubevent")
    public String PostAddSubEvent(@ModelAttribute SubEvent subEvent, HttpSession session){
        organiserDashboardService.AddSubEvent(subEvent);
        return "redirect:viewsubevents";
    }

    @GetMapping("{subeventId}/viewcompetitions")
    public String ViewCompetition(@PathVariable("subEventId") int subEventId, Model model, HttpSession session){
        SubEvent s = organiserDashboardService.getSubEventById(subEventId);
        model.addAttribute("competitions",organiserDashboardService.getCompetions(s));
        return "viewcompetions";
    }

    @GetMapping("{subeventId}/addcompetition")
    public String AddCompetition(@PathVariable("subEventId") int subEventId, Model model, HttpSession session){
        SubEvent s = organiserDashboardService.getSubEventById(subEventId);
        model.addAttribute("competition",new Competition());
        model.addAttribute("subEvent",s);
        return "addsubevent";
    }

    @PostMapping("{subeventId}/addcompetition")
    public String PostAddCompetition(@PathVariable("subEventId") int subEventId,@ModelAttribute Competition competition, HttpSession session){
        SubEvent s = organiserDashboardService.getSubEventById(subEventId);
        competition.setEventId(s.getEventId());
        competition.setSubEventId(s.getSubEventId());
        organiserDashboardService.AddCompetition(competition);
        return "redirect:{subeventId}/viewcompetitions";
    }

    




}
