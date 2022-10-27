package com.example.fms.festmanagement.doa;

import com.example.fms.festmanagement.models.Competition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CompetitionRepository {

    @Autowired
    private JdbcTemplate t;

    public void initCompetition() {

        String x = "CREATE TABLE IF NOT EXISTS Competition (competitionId INT, competitionName varchar(20), prize INT, eventId INT, subeventId INT, PRIMARY KEY (competitionId), FOREIGN KEY (eventId) REFERENCES Event (eventId), FOREIGN KEY (subeventId) REFERENCES SubEvent (subeventId))";

        t.update(x);

    }

    public void insertCompetition(Competition comp) {

        String x = "INSERT INTO Competition VALUES (?, ?, ?, ?, ?)";

        t.update(x);

    }

}
