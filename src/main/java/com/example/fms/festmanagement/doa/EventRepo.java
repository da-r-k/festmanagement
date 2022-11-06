package com.example.fms.festmanagement.doa;

import com.example.fms.festmanagement.models.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EventRepo {

    @Autowired
    private JdbcTemplate t;

    public void insertEvent(Event e) {

        String x = "INSERT INTO Event(eventName, headEmail) VALUES (?, ?)";

        t.update(x, e.getEventName(), e.getHeadEmail());

    }

    public void updateEvent(Event e) {

        String x = "UPDATE Event SET eventName = ?, headEmail = ? WHERE eventId = ?";

        t.update(x, e.getEventName(), e.getHeadEmail(), e.getEventId());

    }

    public void deleteEvent(int e) {

        String x = "DELETE FROM Event WHERE eventId = ?";

        t.update(x, e);

    }

}
