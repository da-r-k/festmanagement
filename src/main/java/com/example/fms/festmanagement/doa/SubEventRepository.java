package com.example.fms.festmanagement.doa;

import com.example.fms.festmanagement.models.SubEvent;
import org.springframework.beans.factory.annotation.Autowired;
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

    public void deleteSubevent(int subeventId) {

        String x = "DELETE FROM SubEvent WHERE subeventId = ?";

        t.update(x, subeventId);

    }

}
