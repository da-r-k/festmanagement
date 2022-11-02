package com.example.fms.festmanagement.service;

import java.util.List;

import com.example.fms.festmanagement.doa.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fms.festmanagement.models.Competition;
import com.example.fms.festmanagement.models.Event;
import com.example.fms.festmanagement.models.Organiser;
import com.example.fms.festmanagement.models.SubEvent;

@Service
public class OrganiserDashboardService {

    @Autowired
    private EventRepo eventRepo;

    @Autowired
    private OrganiserRepo organiserRepo;

    @Autowired
    private SubEventRepo subEventRepo;

    @Autowired
    private CompetitionRepo competitionRepo;

    @Autowired
    private QueriesRepo queriesRepo;

    public Event getEventFromOrganiser(String email){
        return queriesRepo.selectEventFromOrganiser(email);
    }

    public List<Organiser> getOrganisersByEvent(Event e) {
        return queriesRepo.getOrganiserByEvent(e.getEventId());
    }

    public List<SubEvent> getSubEvents(Event e){
        return queriesRepo.getSubEventsByEvent(e.getEventId());
    }

    public void AddSubEvent(SubEvent subEvent) {
        subEventRepo.insertSubEvent(subEvent);
    }

    public List<Competition> getCompetions(SubEvent subEvent) {
        return queriesRepo.getCompetitions(subEvent.getSubEventId(),subEvent.getEventId());
    }

    public SubEvent getSubEventById(int i){
        return queriesRepo.getSubEventById(i);
    }

    public void AddCompetition(Competition competition) {
        competitionRepo.insertCompetition(competition);
    }
}
