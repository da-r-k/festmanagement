package com.example.fms.festmanagement.doa;

import com.example.fms.festmanagement.models.SubEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SubEventRepo {

    @Autowired
    private JdbcTemplate t;

    public void initSubEvent() {

        String x = "CREATE TABLE IF NOT EXISTS SubEvent (subEventId int PRIMARY KEY, startDate DATETIME, endDate DATETIME, venue varchar(64), eventId int)";

        t.update(x);

    }

    public void insertSubEvent(SubEvent s) {

        String x = "INSERT INTO TABLE SubEvent VALUES (?, ?, ?, ?, ?)";

        t.update(x, s.getSubEventId(), s.getStartDate(), s.getEndDate(), s.getVenue(), s.getEventId());

    }

    public void deleteSubEvent(int s) {

        String x = "DELETE FROM SubEvent WHERE subEventId = ?";

        t.update(x, s);

    }

}
