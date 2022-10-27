package com.example.fms.festmanagement.doa;

import com.example.fms.festmanagement.models.SubEvent;
import com.example.fms.festmanagement.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SubEventRepository {

    @Autowired
    private JdbcTemplate t;

    public void initSubevent() {

        String x = "CREATE TABLE IF NOT EXISTS SubEvent (subeventId INT, startDate INT, endDate INT, venue String, evenId INT, PRIMARY KEY (subeventId), FOREIGN KEY (eventId) REFERENCES Event (eventId)))";

        t.update(x);

    }

    public void insertSubevent(SubEvent subevent) {

        String x = "INSERT INTO SubEvent VALUES (?, ?, ?, ?, ?)";

        t.update(x, subevent.getSubeventId(), subevent.getStartDate(), subevent.getEndDate(), subevent.getVenue(), subevent.getEventId());

    }

    public SubEvent selectSubevent(int subeventId) {

        try {

            String x = "SELECT * FROM SubEvent WHERE subeventId = ?";

            return t.queryForObject(x, new BeanPropertyRowMapper<>(SubEvent.class), new Object[] {subeventId});

        }

        catch(EmptyResultDataAccessException e) {

            return null;

        }

    }

    public void deleteSubevent(int subeventId) {

        String x = "DELETE FROM SubEvent WHERE subeventId = ?";

        t.update(x, subeventId);

    }

}
