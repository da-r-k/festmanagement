package com.example.fms.festmanagement.doa;

import com.example.fms.festmanagement.models.SubEvent;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SubEventRepo {

    @Autowired
    private JdbcTemplate t;

    public void initSubEvent() {

        String x = "CREATE TABLE IF NOT EXISTS SubEvent (subEventId INT, subEventName VARCHAR(255), startDate DATETIME, endDate DATETIME, venue VARCHAR(255), eventId INT, PRIMARY KEY (subEventId, eventId))";

        t.update(x);

    }

    public void insertSubEvent(SubEvent s) {

        String x = "INSERT INTO TABLE SubEvent VALUES (?, ?, ?, ?, ?, ?)";

        t.update(x, s.getSubEventId(), s.getSubEventName(), s.getStartDate(), s.getEndDate(), s.getVenue(), s.getEventId());

    }

    public void deleteSubEvent(int s, int e) {

        String x = "DELETE FROM SubEvent WHERE subEventId = ? AND eventId = ?";

        t.update(x, s, e);

    }

}
