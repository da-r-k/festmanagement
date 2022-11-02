package com.example.fms.festmanagement.service;

import java.util.Date;
import java.util.List;

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

    public List<SubEvent> getSubEventToday(){
        return subEventRepo.getSubEventByDate(new Date());
    }

    public List<Event> getEventFromSubEvent(List<SubEvent> s){
        return eventRepo.getEventFromSubevent(s);
    }

}
