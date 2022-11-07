package com.example.fms.festmanagement.controller;

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

import jakarta.servlet.http.HttpSession;

import com.example.fms.festmanagement.models.Cart;
import com.example.fms.festmanagement.models.CartItemDetails;
import com.example.fms.festmanagement.models.Competition;
import com.example.fms.festmanagement.models.Event;
import com.example.fms.festmanagement.models.Item;
import com.example.fms.festmanagement.models.Participant;
import com.example.fms.festmanagement.models.Participation;
import com.example.fms.festmanagement.models.SubEvent;
import com.example.fms.festmanagement.models.Transaction;
import com.example.fms.festmanagement.models.User;
import com.example.fms.festmanagement.service.AuthenticationService;
import com.example.fms.festmanagement.service.DashboardService;
import com.example.fms.festmanagement.service.MessageService;
import com.example.fms.festmanagement.service.OrganiserDashboardService;

@Controller
public class UserDashboardController extends Helper {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private DashboardService dashboardService;

    @Autowired
    private OrganiserDashboardService organiserDashboardService;

    @Autowired 
    private MessageService messageService;

    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session, RedirectAttributes attributes) {
        if (!isAuthenticated(session)) {
            return "redirect:/";
        }
        addDefaultAttributes(model, session);
        if(!model.getAttribute("userRole").equals("participant")){
            return "redirect:accessdenied";
        }
        model.addAttribute("subEvents", dashboardService.getSubEventToday());
        model.addAttribute("correspondingevents", dashboardService.getEventFromSubEvent(dashboardService.getSubEventToday()));        
        return "dashboard";
    }

    @GetMapping("showevents")
    public String ShowEvents(Model model, HttpSession session){
        if (!isAuthenticated(session)) {
            return "redirect:/";
        }
        addDefaultAttributes(model, session);
        if(!model.getAttribute("userRole").equals("participant")){
            return "redirect:accessdenied";
        }
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
    public String Participate(@PathVariable("eventId") int eventId, @PathVariable("subEventId") int subEventId, @PathVariable("competitionId") int competitionId, Model model, HttpSession session, RedirectAttributes attributes){
        if (!isAuthenticated(session)) {
            return "redirect:/";
        }
        addDefaultAttributes(model, session);
        if(!model.getAttribute("userRole").equals("participant")){
            return "redirect:accessdenied";
        }
        Participation p=new Participation();
        addDefaultAttributes(model, session);
        p.setParticipantEmail(authenticationService.getCurrentUser(session));
        p.setEventId(eventId);
        p.setSubEventId(subEventId);
        p.setLeaderBoardPosition(null);
        p.setCompetitionId(competitionId);
        System.out.println(p.toString());
        dashboardService.addParticipation(p);
        messageService.redirectWithSuccess(attributes,"Successfully registered");
        return "redirect:/showevents";
    }

    @GetMapping("{eventId}_{subEventId}_{competitionId}_leaderboard")
    public String Leaderboard(@PathVariable("eventId") int eventId, @PathVariable("subEventId") int subEventId, @PathVariable("competitionId") int competitionId, Model model, HttpSession session){
        if (!isAuthenticated(session)) {
            return "redirect:/";
        }
        addDefaultAttributes(model, session);
        if(!model.getAttribute("userRole").equals("participant")){
            return "redirect:accessdenied";
        }
        Event e= organiserDashboardService.getEvent(eventId);
        SubEvent s = organiserDashboardService.getSubEventById(subEventId,e);
        Competition c =organiserDashboardService.getCompetitionById(competitionId,s);
        model.addAttribute("leaderboard",dashboardService.getLeaderboard(eventId,subEventId,competitionId));
        model.addAttribute("e",e);
        model.addAttribute("s",s);
        model.addAttribute("c",c);
        return "leaderboard";
    }

    @GetMapping("faq")
    public String Faq(Model model, HttpSession session){
        return "faq";
    }

    @GetMapping("goodies")
    public String Goodies(Model model, HttpSession session){
        if (!isAuthenticated(session)) {
            return "redirect:/";
        }
        addDefaultAttributes(model, session);
        if(!model.getAttribute("userRole").equals("participant")){
            return "redirect:accessdenied";
        }
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
        if (!isAuthenticated(session)) {
            return "redirect:/";
        }
        addDefaultAttributes(model, session);
        if(!model.getAttribute("userRole").equals("participant")){
            return "redirect:accessdenied";
        }
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
        if (!isAuthenticated(session)) {
            return "redirect:/";
        }
        addDefaultAttributes(model, session);
        if(!model.getAttribute("userRole").equals("participant")){
            return "redirect:accessdenied";
        }
        Cart c=dashboardService.getActiveCart(authenticationService.getCurrentUser(session));
        
        if(c==null){
            dashboardService.makeCart(authenticationService.getCurrentUser(session));
            c=dashboardService.getActiveCart(authenticationService.getCurrentUser(session));
        }
        dashboardService.addtoCart(c,itemId);
        return "redirect:/goodies";
    }

    @PostMapping("updateitemincart")
    public String UpdateGoodies(Model model, HttpSession session, @ModelAttribute("cid") CartItemDetails cid){
        if (!isAuthenticated(session)) {
            return "redirect:/";
        }
        addDefaultAttributes(model, session);
        if(!model.getAttribute("userRole").equals("participant")){
            return "redirect:accessdenied";
        }
        System.out.println(cid.toString());
        dashboardService.updateCart(cid);
        return "redirect:/cart";
    }

    @GetMapping("{itemId}/removeitem")
    public String RemoveGoodie(Model model, HttpSession session,@PathVariable("itemId") int itemId){
        if (!isAuthenticated(session)) {
            return "redirect:/";
        }
        addDefaultAttributes(model, session);
        if(!model.getAttribute("userRole").equals("participant")){
            return "redirect:accessdenied";
        }
        Cart c=dashboardService.getActiveCart(authenticationService.getCurrentUser(session));
        dashboardService.removeFromCart(c,itemId);
        return "redirect:/cart";
    }

    @GetMapping("previoustransactions")
    public String PreviousTransactions(Model model, HttpSession session){
        if (!isAuthenticated(session)) {
            return "redirect:/";
        }
        addDefaultAttributes(model, session);
        if(!model.getAttribute("userRole").equals("participant")){
            return "redirect:accessdenied";
        }
        List<Cart> c=dashboardService.getPreviousCarts(authenticationService.getCurrentUser(session));
        List<Transaction> t = new ArrayList<Transaction>();
        for(Cart cart:c){
            t.add(dashboardService.getTransaction(cart));
        }
        List<List<Item>>ret=new ArrayList<List<Item>>();
        List<List<CartItemDetails>>ret2=new ArrayList<List<CartItemDetails>>();
        for(Cart cart:c){
            List<CartItemDetails> cid =dashboardService.getCartItemDetails(cart);
            ret2.add(cid);
            ret.add(dashboardService.getItemDetails(cid));
        }
        model.addAttribute("transactions",t);
        model.addAttribute("items",ret);
        model.addAttribute("cid",ret2);
        return "previoustransactions";
    }

    @GetMapping("checkout")
    public String Checkout(Model model, HttpSession session){
        if (!isAuthenticated(session)) {
            return "redirect:/";
        }
        addDefaultAttributes(model, session);
        if(!model.getAttribute("userRole").equals("participant")){
            return "redirect:accessdenied";
        }
        model.addAttribute("user",dashboardService.getParticipant(authenticationService.getCurrentUser(session)));
        Cart c=dashboardService.getActiveCart(authenticationService.getCurrentUser(session));
        List<CartItemDetails>cid=dashboardService.getCartItemDetails(c);
        List<Item> items=dashboardService.getItemDetails(cid);
        Participant p=dashboardService.getParticipant(authenticationService.getCurrentUser(session));
        model.addAttribute("cid",cid);
        model.addAttribute("items",items);
        model.addAttribute("amount",dashboardService.calculateAmount(cid));
        model.addAttribute("participant",p);

        return "checkout";
    }

    @PostMapping("checkout")
    public String PostCheckout(Model model, HttpSession session, @ModelAttribute Participant p){
        if (!isAuthenticated(session)) {
            return "redirect:/";
        }
        addDefaultAttributes(model, session);
        if(!model.getAttribute("userRole").equals("participant")){
            return "redirect:accessdenied";
        }
        Cart c=dashboardService.getActiveCart(authenticationService.getCurrentUser(session));
        dashboardService.updateParticipant(p);
        dashboardService.createTransaction(c);
        model.addAttribute("transaction",dashboardService.getTransaction(c));
        model.addAttribute("user",p);
        return "receipt";
    }

}
