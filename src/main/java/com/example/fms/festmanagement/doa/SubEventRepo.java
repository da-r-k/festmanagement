package com.example.fms.festmanagement.doa;

import com.example.fms.festmanagement.models.SubEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SubEventRepo {

    @Autowired
    private JdbcTemplate t;

    public void insertSubEvent(SubEvent s) {

        String x = "INSERT INTO SubEvent (subEventName,startDate,endDate,venue,eventId) VALUES (?, ?, ?, ?, ?)";

        t.update(x, s.getSubEventName(), s.getStartDate(), s.getEndDate(), s.getVenue(), s.getEventId());

    }

    public void deleteSubEvent(int s, int e) {

        String x = "DELETE FROM SubEvent WHERE subEventId = ? AND eventId = ?";

        t.update(x, s, e);

    }

}
