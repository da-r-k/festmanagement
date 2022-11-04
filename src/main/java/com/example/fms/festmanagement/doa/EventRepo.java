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

    public void initEvent() {

        String x = "CREATE TABLE IF NOT EXITS Event (eventId INT PRIMARY KEY, eventName VARCHAR(255), headEmail VARCHAR(255))";

        t.update(x);

    }

    public void insertEvent(Event e) {

        String x = "INSERT INTO EVENT VALUES (?, ?, ?)";

        t.update(x, e.getEventId(), e.getEventName(), e.getHeadEmail());

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
