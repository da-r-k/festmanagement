package com.example.fms.festmanagement.service;

import java.util.Date;
import java.util.List;

import com.example.fms.festmanagement.doa.QueriesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fms.festmanagement.doa.EventRepo;
import com.example.fms.festmanagement.doa.SubEventRepo;
import com.example.fms.festmanagement.models.Event;
import com.example.fms.festmanagement.models.SubEvent;

@Service
public class DashboardService {

    @Autowired
    private SubEventRepo subEventRepo;

    @Autowired
    private EventRepo eventRepo;

    @Autowired
    private QueriesRepo queriesRepo;

    public List<SubEvent> getSubEventToday(){
        return queriesRepo.getSubEventByDate(new Date());
    }

    public List<Event> getEventFromSubEvent(List<SubEvent> s){
        return queriesRepo.getEventFromSubEvent(s);
    }

}
