package com.example.fms.festmanagement.doa;

import com.example.fms.festmanagement.models.SubEvent;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SubEventRepo {

    @Autowired
    private JdbcTemplate t;

    public void initSubEvent() {

        String x = "CREATE TABLE IF NOT EXISTS SubEvent (subEventId INT, startDate DATETIME, endDate DATETIME, venue VARCHAR(255), eventId INT, PRIMARY KEY (subEventId, eventId))";

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

    public List<SubEvent> getSubEventByDate(Date d) {

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String dd = df.format(d);
        String x = "SELECT * FROM SubEvent WHERE startDate = '"+dd+"'";
        

        

        return t.query(x, new BeanPropertyRowMapper<>(SubEvent.class));

    }

}
