package com.example.fms.festmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;

import com.example.fms.festmanagement.models.Cart;
import com.example.fms.festmanagement.models.CartItemDetails;
import com.example.fms.festmanagement.models.Competition;
import com.example.fms.festmanagement.models.Event;
import com.example.fms.festmanagement.models.Item;
import com.example.fms.festmanagement.models.Participant;
import com.example.fms.festmanagement.models.Participation;
import com.example.fms.festmanagement.models.SubEvent;
import com.example.fms.festmanagement.models.User;
import com.example.fms.festmanagement.service.AuthenticationService;
import com.example.fms.festmanagement.service.DashboardService;
import com.example.fms.festmanagement.service.OrganiserDashboardService;

@Controller
public class UserDashboardController extends Helper {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private DashboardService dashboardService;

    @Autowired
    private OrganiserDashboardService organiserDashboardService;

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
        else{
            return "redirect:admindashboard";
        }
    }

    @GetMapping("showevents")
    public String ShowEvents(Model model, HttpSession session){
        List<Event> e=dashboardService.showEvents();
        List<List<SubEvent>>s=dashboardService.showSubEvents(e);
        List<List<List<Competition>>>c=dashboardService.showCompetitions(s);
        List<List<List<Boolean>>>p=dashboardService.checkParticipation(authenticationService.getCurrentUser(session),c);
        model.addAttribute("events", e);
        model.addAttribute("subevents", s);
        model.addAttribute("competitions", c);
        model.addAttribute("participations", p);
        return "showevents";
    }

    @GetMapping("{eventId}/{subEventId}/{competitionId}/participate")
    public String Participate(@PathVariable("eventId") int eventId, @PathVariable("subEventId") int subEventId, @PathVariable("competitionId") int competitionId, Model model, HttpSession session){
        Participation p=new Participation();
        addDefaultAttributes(model, session);
        p.setParticipantEmail(authenticationService.getCurrentUser(session));
        p.setEventId(eventId);
        p.setSubEventId(subEventId);
        p.setLeaderBoardPosition(null);
        p.setCompetitionId(competitionId);
        System.out.println(p.toString());
        dashboardService.addParticipation(p);
        return "redirect:/showevents";
    }

    @GetMapping("{eventId}_{subEventId}_{competitionId}_leaderboard")
    public String Leaderboard(@PathVariable("eventId") int eventId, @PathVariable("subEventId") int subEventId, @PathVariable("competitionId") int competitionId, Model model, HttpSession session){
        Event e= organiserDashboardService.getEvent(eventId);
        SubEvent s = organiserDashboardService.getSubEventById(subEventId,e);
        Competition c =organiserDashboardService.getCompetitionById(competitionId,s);
        model.addAttribute("leaderboard",dashboardService.getLeaderboard(eventId,subEventId,competitionId));
        return "leaderboard";
    }

    @GetMapping("faq")
    public String Faq(Model model, HttpSession session){
        return "faq";
    }

    @GetMapping("goodies")
    public String Goodies(Model model, HttpSession session){
        Cart c=dashboardService.getActiveCart(authenticationService.getCurrentUser(session));
        if(c==null){
            dashboardService.makeCart(authenticationService.getCurrentUser(session));
            c=dashboardService.getActiveCart(authenticationService.getCurrentUser(session));
        }
        List<Item> g=dashboardService.getGoodies();
        List<Boolean> added = dashboardService.checkAdded(g,c);
        model.addAttribute("goodies", g);
        model.addAttribute("added", added);
        return "goodies";
    }

    @GetMapping("cart")
    public String Cart(Model model, HttpSession session){
        Cart c=dashboardService.getActiveCart(authenticationService.getCurrentUser(session));
        if(c==null){
            dashboardService.makeCart(authenticationService.getCurrentUser(session));
            c=dashboardService.getActiveCart(authenticationService.getCurrentUser(session));
        }
        else{
            List<CartItemDetails> cid=dashboardService.getCartItemDetails(c);
            List<Item> items=dashboardService.getItemDetails(cid);
            model.addAttribute("cid",cid);
            model.addAttribute("items",items);
            model.addAttribute("amount",dashboardService.calculateAmount(cid));
        }
        return "cart";
    }

    @GetMapping("{itemId}/additem")
    public String AddGoodies(Model model, HttpSession session, @PathVariable("itemId") int itemId){
        Cart c=dashboardService.getActiveCart(authenticationService.getCurrentUser(session));
        if(c==null){
            dashboardService.makeCart(authenticationService.getCurrentUser(session));
            c=dashboardService.getActiveCart(authenticationService.getCurrentUser(session));
        }
        dashboardService.addtoCart(c,itemId);
        return "redirect:/cart";
    }

    @GetMapping("{itemId}/{quantity}/update")
    public String AddGoodies(Model model, HttpSession session, @PathVariable("itemId") int itemId, @PathVariable("quantity") int quantity){
        Cart c=dashboardService.getActiveCart(authenticationService.getCurrentUser(session));
        dashboardService.updateCart(c,itemId,quantity);
        return "redirect:/cart";
    }

    @GetMapping("{itemId}/remove")
    public String RemoveGoodie(Model model, HttpSession session,@PathVariable("itemId") int itemId){
        Cart c=dashboardService.getActiveCart(authenticationService.getCurrentUser(session));
        dashboardService.removeFromCart(c,itemId);
        return "redirect:/cart";
    }

    @GetMapping("previoustransactions")
    public String PreviousTransactions(Model model, HttpSession session){
        List<Cart> c=dashboardService.getPreviousCarts(authenticationService.getCurrentUser(session));
        model.addAttribute("c",c);
        return "previoustransactions";
    }

    @GetMapping("checkout")
    public String Checkout(Model model, HttpSession session){
        model.addAttribute("user",dashboardService.getParticipant(authenticationService.getCurrentUser(session)));
        Cart c=dashboardService.getActiveCart(authenticationService.getCurrentUser(session));
        List<CartItemDetails>cid=dashboardService.getCartItemDetails(c);
        model.addAttribute("cid",cid);
        model.addAttribute("amount",dashboardService.calculateAmount(cid));

        return "checkout";
    }

    @PostMapping("checkout")
    public String PostCheckout(Model model, HttpSession session, @ModelAttribute Participant p){
        Cart c=dashboardService.getActiveCart(authenticationService.getCurrentUser(session));
        dashboardService.updateParticipant(p);
        dashboardService.createTransaction(c);
        model.addAttribute("transaction",dashboardService.getTransaction(c));
        model.addAttribute("user",p);
        return "receipt";
    }

}
