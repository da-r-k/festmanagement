package com.example.fms.festmanagement.doa;

import com.example.fms.festmanagement.models.Event;
import com.example.fms.festmanagement.models.SubEvent;
import com.example.fms.festmanagement.models.User;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EventRepo {

    @Autowired
    private JdbcTemplate t;

    private void initEvent() {

        String x = "CREATE TABLE IF NOT EXITS Event (eventId INT PRIMARY KEY, eventName VARCHAR(255), headEmail VARCHAR(255))";

        t.update(x);

    }

    private void insertEvent(Event e) {

        String x = "INSERT INTO EVENT VALUES (?, ?, ?)";

        t.update(x, e.getEventId(), e.getEventName(), e.getHeadEmail());

    }

    public Event selectEvent(int v) {

        try {

            String x = "SELECT * FROM Event WHERE eventId = ?";

            return t.queryForObject(x, new BeanPropertyRowMapper<>(Event.class), new Object[] { v });

        }

        catch (EmptyResultDataAccessException e) {

            return null;

        }

    }

    public Event selectEventFromOrganiser(String v) {

        try {

            String x = "SELECT * FROM Event WHERE headEmail = '?'";

            return t.queryForObject(x, new BeanPropertyRowMapper<>(Event.class), new Object[] { v });

        }

        catch (EmptyResultDataAccessException e) {

            return null;

        }

    }

    private void updateEvent(Event e) {

        String x = "UPDATE Event SET eventName = ?, headEmail = ? WHERE eventId = ?";

        t.update(x, e.getEventName(), e.getHeadEmail(), e.getEventId());

    }

    private void deleteEvent(int e) {

        String x = "DELETE FROM Event WHERE eventId = ?";

        t.update(x, e);

    }

    public List<Event> getEventFromSubevent(List<SubEvent> s) {
        List<Event>e=new ArrayList<Event>();
        for (int i = 0; i < s.size(); i++) 
        {
            e.add(selectEvent(s.get(i).getEventId()));
        }
        return e;
    }

}
