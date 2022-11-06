package com.example.fms.festmanagement.service;

import java.util.List;

import com.example.fms.festmanagement.doa.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fms.festmanagement.doa.CompetitionRepo;
import com.example.fms.festmanagement.doa.EventRepo;
import com.example.fms.festmanagement.doa.OrganiserRepo;
import com.example.fms.festmanagement.doa.QueriesRepo;
import com.example.fms.festmanagement.doa.SubEventRepo;
import com.example.fms.festmanagement.models.Competition;
import com.example.fms.festmanagement.models.Event;
import com.example.fms.festmanagement.models.Fund;
import com.example.fms.festmanagement.models.Item;
import com.example.fms.festmanagement.models.Organiser;
import com.example.fms.festmanagement.models.Participant;
import com.example.fms.festmanagement.models.Participation;
import com.example.fms.festmanagement.models.Sponsor;
import com.example.fms.festmanagement.models.SubEvent;
import com.example.fms.festmanagement.models.User;

@Service
public class AdminService {

    @Autowired
    private EventRepo eventRepo;

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private QueriesRepo queriesRepo;

    @Autowired
    private SponsorRepo sponsorRepo;

    @Autowired
    private FundRepo fundRepo;

    public void addEvent(User user, Event event, Organiser organiser) {
        organiser.setOrganiserEmail(user.getUserEmail());
        user.setRole("organiser");
        userRepo.insertUser(user);
        queriesRepo.addNewEvent(event,organiser);
    }

    public List<Event> getAllEvents() {
        return queriesRepo.getAllEvents();
    }

    public void deleteEvent(int eventId) {
        Event e=queriesRepo.selectEvent(eventId);
        userRepo.deleteUser(e.getHeadEmail());
        eventRepo.deleteEvent(eventId);
    }

    public void updateItem(Item item) {
        itemRepo.updateItem(item);
    }

    public void addItem(Item item) {
        itemRepo.insertItem(item);
    }

    public List<Item> getAllItems() {
        return queriesRepo.getAllItems();
    }

    public List<Sponsor> getSponsors() {
        return queriesRepo.getAllSponsors();
    }

    public void addSponsors(Sponsor sponsor) {
        sponsorRepo.insertSponsor(sponsor);
    }

    public void deleteSponsors(int sponsorId) {
        sponsorRepo.deleteSponsor(sponsorId);
    }

    public void addFund(Fund fund) {
        fundRepo.insertFund(fund);
    }
    
}
