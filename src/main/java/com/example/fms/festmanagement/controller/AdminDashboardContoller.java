package com.example.fms.festmanagement.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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
import com.example.fms.festmanagement.models.Item;
import com.example.fms.festmanagement.models.Organiser;
import com.example.fms.festmanagement.models.Participation;
import com.example.fms.festmanagement.models.Sponsor;
import com.example.fms.festmanagement.models.SubEvent;
import com.example.fms.festmanagement.models.User;
import com.example.fms.festmanagement.service.AdminService;
import com.example.fms.festmanagement.service.AuthenticationService;
import com.example.fms.festmanagement.service.DashboardService;
import com.example.fms.festmanagement.service.OrganiserDashboardService;
import com.example.fms.festmanagement.service.RegistrationService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminDashboardContoller extends Helper{

    @Autowired
    private AdminService adminService;

    @GetMapping("admindashboard")
    public String OrganiserDashboard(Model model, HttpSession session, RedirectAttributes attributes){
        return "admindashboard";
    }

    @GetMapping("viewevents")
    public String ViewEvents(Model model, HttpSession session, RedirectAttributes attributes){
        model.addAttribute(adminService.getAllEvents());
        return "viewevents";
    }

    @GetMapping("addevent")
    public String AddEvent(Model model, HttpSession session, RedirectAttributes attributes){
        model.addAttribute("event", new Event());
        model.addAttribute("organiser", new Organiser());
        model.addAttribute("user", new User());
        return "addevent";
    }

    @PostMapping("addevent")
    public String PostAddEvent(@ModelAttribute User user, @ModelAttribute Organiser organiser, @ModelAttribute Event event, Model model, HttpSession session, RedirectAttributes attributes){
        adminService.addEvent(user,event,organiser);
        return "redirect:/viewevents";
    }

    @GetMapping("{eventId}/delevent")
    public String DeleteEvent(@PathVariable("eventId") int eventId,Model model, HttpSession session, RedirectAttributes attributes){
        adminService.deleteEvent(eventId);
        return "redirect:/viewevents";
    }

    @GetMapping("edititems")
    public String EditItems(Model model, HttpSession session, RedirectAttributes attributes){
        model.addAttribute("allitems",adminService.getAllItems());
        model.addAttribute("newitem",new Item());
        return "edititems";
    }

    @PostMapping("{ItemId}/update")
    public String UpdateItem(@ModelAttribute Item item,Model model, HttpSession session, RedirectAttributes attributes){
        adminService.updateItem(item);
        return "redirect:/edititems";
    }

    @PostMapping("additem")
    public String AddItem(@ModelAttribute Item item,Model model, HttpSession session, RedirectAttributes attributes){
        adminService.addItem(item);
        return "redirect:/edititems";
    }

    @GetMapping("editsponsors")
    public String EditSponsors(Model model, HttpSession session, RedirectAttributes attributes){
        model.addAttribute("sponsors",adminService.getSponsors());
        model.addAttribute("newsponsor",new Sponsor());
        return "editsponsors";
    }

    @PostMapping("addsponsors")
    public String EditSponsors(@ModelAttribute Sponsor sponsor,Model model, HttpSession session, RedirectAttributes attributes){
        adminService.addSponsors(sponsor);
        return "redirect:/editsponsors";
    }

    

}
