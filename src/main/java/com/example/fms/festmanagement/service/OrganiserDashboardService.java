package com.example.fms.festmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fms.festmanagement.doa.EventRepo;
import com.example.fms.festmanagement.doa.OrganiserRepo;
import com.example.fms.festmanagement.models.Event;
import com.example.fms.festmanagement.models.Organiser;

@Service
public class OrganiserDashboardService {

    @Autowired
    private EventRepo eventRepo;

    @Autowired
    private OrganiserRepo organiserRepo;

    public Event getEventFromOrganiser(String email){
        return eventRepo.selectEventFromOrganiser(email);
    }

    public List<Organiser> getOrganisersByEvent(Event e) {
        return organiserRepo.getOrganiserByEvent(e.getEventId());
    }
    
}
