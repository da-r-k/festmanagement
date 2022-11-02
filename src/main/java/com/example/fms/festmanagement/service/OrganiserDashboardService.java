package com.example.fms.festmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fms.festmanagement.doa.CompetitionRepo;
import com.example.fms.festmanagement.doa.EventRepo;
import com.example.fms.festmanagement.doa.OrganiserRepo;
import com.example.fms.festmanagement.doa.QueriesRepo;
import com.example.fms.festmanagement.doa.SubEventRepo;
import com.example.fms.festmanagement.models.Competition;
import com.example.fms.festmanagement.models.Event;
import com.example.fms.festmanagement.models.Organiser;
import com.example.fms.festmanagement.models.Participant;
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

    public SubEvent getSubEventById(int i, int j){
        return queriesRepo.getSubEventById(i,j);
    }

    public void AddCompetition(Competition competition) {
        competitionRepo.insertCompetition(competition);
    }

    public Competition getCompetitionById(int competitionId, int subEventId, int eventId) {
        return queriesRepo.getCompetitionById(competitionId,subEventId,eventId);
    }

    public List<Participant> getAllParticipants(int competitionId, int eventId, int subEventId) {
        return queriesRepo.getAllParticipants(competitionId,eventId,subEventId);
    }
}
