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
import com.example.fms.festmanagement.models.Event;
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
        Event e=organiserDashboardService.getEventFromOrganiser(authenticationService.getCurrentUser(session));
        model.addAttribute("organisers", organiserDashboardService.getOrganisersByEvent(e));
        return "vieworganisers";
    } 

    @GetMapping("viewsubevents")
    public String ViewSubEvents(Model model,HttpSession session){
        Event e=organiserDashboardService.getEventFromOrganiser(authenticationService.getCurrentUser(session));
        model.addAttribute("subevents",organiserDashboardService.getSubEvents(e));
        return "viewsubevents";
    }

    @GetMapping("addsubevent")
    public String AddSubEvents(Model model, HttpSession session, RedirectAttributes attributes){
        Event e=organiserDashboardService.getEventFromOrganiser(authenticationService.getCurrentUser(session));
        model.addAttribute("subevent",new SubEvent());
        model.addAttribute("event",e);
        return "addsubevent";
    }

    @PostMapping("addsubevent")
    public String PostAddSubEvent(@ModelAttribute SubEvent subEvent, HttpSession session){
        Event e=organiserDashboardService.getEventFromOrganiser(authenticationService.getCurrentUser(session));
        subEvent.setEventId(e.getEventId());
        organiserDashboardService.AddSubEvent(subEvent);
        return "redirect:viewsubevents";
    }

    @GetMapping("{subeventId}/viewcompetitions")
    public String ViewCompetition(@PathVariable("subEventId") int subEventId, Model model, HttpSession session){
        Event e=organiserDashboardService.getEventFromOrganiser(authenticationService.getCurrentUser(session));
        SubEvent s = organiserDashboardService.getSubEventById(subEventId,e);
        model.addAttribute("competitions",organiserDashboardService.getCompetions(s));
        return "viewcompetions";
    }

    @GetMapping("{subeventId}/addcompetition")
    public String AddCompetition(@PathVariable("subEventId") int subEventId, Model model, HttpSession session){
        Event e=organiserDashboardService.getEventFromOrganiser(authenticationService.getCurrentUser(session));
        SubEvent s = organiserDashboardService.getSubEventById(subEventId,e);
        model.addAttribute("competition",new Competition());
        model.addAttribute("subEvent",s);
        return "addsubevent";
    }

    @PostMapping("{subeventId}/addcompetition")
    public String PostAddCompetition(@PathVariable("subEventId") int subEventId,@ModelAttribute Competition competition, HttpSession session){
        Event e=organiserDashboardService.getEventFromOrganiser(authenticationService.getCurrentUser(session));
        SubEvent s = organiserDashboardService.getSubEventById(subEventId,e);
        competition.setEventId(s.getEventId());
        competition.setSubEventId(s.getSubEventId());
        organiserDashboardService.AddCompetition(competition);
        return "redirect:{subeventId}/viewcompetitions";
    }

    @GetMapping("{subeventId}/{competitionId}/viewparticipants")
    public String ViewParticipants(Model model, @PathVariable("subeventId") int subEventId, @PathVariable("competitionId") int competitionId, HttpSession session){
        Event e=organiserDashboardService.getEventFromOrganiser(authenticationService.getCurrentUser(session));
        SubEvent s = organiserDashboardService.getSubEventById(subEventId,e);
        Competition c =organiserDashboardService.getCompetitionById(competitionId,s);
        model.addAttribute(organiserDashboardService.getAllParticipants(c));
        return "viewparticipants";
    }

    @GetMapping("{subeventId}/{competitionId}/{participantEmail}/del")
    public String DeleteParticipant(Model model, @PathVariable("subeventId") int subEventId, @PathVariable("competitionId") int competitionId,@PathVariable("participantEmail") String participantEmail, HttpSession session){
        Event e=organiserDashboardService.getEventFromOrganiser(authenticationService.getCurrentUser(session));
        SubEvent s = organiserDashboardService.getSubEventById(subEventId,e);
        Competition c =organiserDashboardService.getCompetitionById(competitionId,s);
        organiserDashboardService.deleteParticipation(participantEmail,c);
        return "redirect:/viewparticipants";
    }

    @GetMapping("{subeventId}/{competitionId}/updateLeaderboard")
    public String UpdateLeaderboard(Model model,@PathVariable("subeventId") int subEventId, @PathVariable("competitionId") int competitionId,@PathVariable("participantEmail") String participantEmail, HttpSession session){
        Event e=organiserDashboardService.getEventFromOrganiser(authenticationService.getCurrentUser(session));
        SubEvent s = organiserDashboardService.getSubEventById(subEventId,e);
        Competition c =organiserDashboardService.getCompetitionById(competitionId,s);
        model.addAttribute("participants",organiserDashboardService.getAllParticipations(c));
        return "updateLeaderboard";
    }

    @PostMapping("{subeventId}/{competitionId}/updateLeaderboard")
    public String PostUpdateLeaderboard(@ModelAttribute List<Participation> allParticipations, HttpSession session){
        
    }




    

}
