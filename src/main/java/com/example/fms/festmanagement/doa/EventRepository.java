package com.example.fms.festmanagement.doa;

import com.example.fms.festmanagement.models.Event;
import com.example.fms.festmanagement.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EventRepository {

    @Autowired
    private JdbcTemplate t;

    public void initEvent() {

        String x = "CREATE TABLE IF NOT EXISTS Event (eventId INT, eventName varchar(20), headId INT, PRIMARY KEY (eventId))";

        t.update(x);

    }

    public void insertEvent(Event event) {

        String x = "INSERT INTO Event VALUES (?, ?, ?)";

        t.update(x, event.getEventId(), event.getEventName(), event.getHeadId());

    }

    public Event selectEvent(int eventId) {

        try {

            String x = "SELECT * FROM Event WHERE eventId = ?";

            return t.queryForObject(x, new BeanPropertyRowMapper<>(Event.class), new Object[] {eventId});

        }

        catch(EmptyResultDataAccessException e) {

            return null;

        }

    }

    public void deleteEvent(int eventId) {

        String x = "DELETE FROM Event WHERE eventId = ?";

        t.update(x, eventId);

    }

}
