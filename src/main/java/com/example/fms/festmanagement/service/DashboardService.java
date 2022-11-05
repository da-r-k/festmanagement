package com.example.fms.festmanagement.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.fms.festmanagement.doa.QueriesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fms.festmanagement.doa.CartItemDetailsRepo;
import com.example.fms.festmanagement.doa.CartRepo;
import com.example.fms.festmanagement.doa.EventRepo;
import com.example.fms.festmanagement.doa.ParticipantRepo;
import com.example.fms.festmanagement.doa.SubEventRepo;
import com.example.fms.festmanagement.doa.TransactionRepo;
import com.example.fms.festmanagement.models.Cart;
import com.example.fms.festmanagement.models.CartItemDetails;
import com.example.fms.festmanagement.models.Competition;
import com.example.fms.festmanagement.models.Event;
import com.example.fms.festmanagement.models.Item;
import com.example.fms.festmanagement.models.Participant;
import com.example.fms.festmanagement.models.Participation;
import com.example.fms.festmanagement.models.SubEvent;
import com.example.fms.festmanagement.models.Transaction;

@Service
public class DashboardService {

    @Autowired
    private SubEventRepo subEventRepo;

    @Autowired
    private EventRepo eventRepo;

    @Autowired
    private QueriesRepo queriesRepo;

    @Autowired
    private CartItemDetailsRepo cartItemDetailsRepo;

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private TransactionRepo transactionRepo;

    @Autowired 
    private ParticipantRepo participantRepo;

    public List<SubEvent> getSubEventToday(){
        return queriesRepo.getSubEventByDate(new Date());
    }

    public List<Event> getEventFromSubEvent(List<SubEvent> s){
        return queriesRepo.getEventFromSubEvent(s);
    }

    public Cart getActiveCart(String currentUser) {
        return queriesRepo.activeCart(currentUser);
    }

    public List<Item> getGoodies() {
        return queriesRepo.getAllItems();
    }

    public List<Boolean> checkAdded(List<Item> g, Cart c) {
        List<Boolean>ret=new ArrayList<Boolean>();
        for(Item x:g){
            Boolean b=queriesRepo.checkAdded(x,c);
            ret.add(b);
        }
        return ret;
    }

    public List<CartItemDetails> getCartItemDetails(Cart c) {
        return queriesRepo.getCartItems(c);
    }

    public void addtoCart(Cart c, int itemId) {
        CartItemDetails details=new CartItemDetails();
        details.setQuantity(1);
        details.setCartId(c.getCartId());
        details.setItemId(itemId);
        cartItemDetailsRepo.insertCartItemDetails(details);
    }

    public Cart makeCart(String currentUser) {
        Cart c=new Cart();
        c.setParticipantEmail(currentUser);
        cartRepo.insertCart(c);
    }

    public void updateCart(Cart c, int itemId, int quantity) {
        cartItemDetailsRepo.updateCartItemDetails(c,itemId,quantity);
    }

    public void removeFromCart(Cart c, int itemId) {
        cartItemDetailsRepo.deleteCartItemDetails(c,itemId);
    }

    public List<Cart> getPreviousCarts(String currentUser) {
        queriesRepo.getPreviousCarts(currentUser);
    }

    public int calculateAmount(List<CartItemDetails> cid) {
        int ret=0;
        for(CartItemDetails c:cid){
            ret+=queriesRepo.getAmount(c);
        }
        return ret;
    }

    public Participant getParticipant(String currentUser) {
        return queriesRepo.getParticipant(currentUser);
    }

    public Transaction createTransaction(Cart c) {
        Transaction t = new Transaction();
        t.setCartId(c.getCartId());
        t.setDateTime(new Date());
        t.setAmount(calculateAmount(getCartItemDetails(c)));
        return transactionRepo.insertTransaction(t);
    }

    public void updateParticipant(Participant p) {
        participantRepo.updateParticipant(p);
    }

    public List<Event> showEvents() {
        return queriesRepo.getAllEvents();
    }

    public List<List<SubEvent>> showSubEvents(List<Event> e) {
        List<List<SubEvent>>ret = new ArrayList<List<SubEvent>>();
        for(Event event:e){
            List<SubEvent>s=queriesRepo.getSubEventsByEvent(event.getEventId());
        }
        return ret;
    }

    public List<List<List<Competition>>> showCompetitions(List<List<SubEvent>> s) {
        List<List<List<Competition>>> ret=new ArrayList<List<List<List<Competition>>>();
        return ret;
    }

    public List<List<List<Boolean>>> checkParticipation(String currentUser, List<List<List<Competition>>> c) {
        return null;
    }

    public void addParticipation(Participation p) {
    }

    public List<Participation> getLeaderboard(int eventId, int subEventId, int competitionId) {
        return null;
    }



}
