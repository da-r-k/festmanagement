package com.example.fms.festmanagement.doa;

import com.example.fms.festmanagement.models.Competition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CompetitionRepo {

    @Autowired
    private JdbcTemplate t;

    public void initCompetition() {

        String x = "CREATE TABLE IF NOT EXISTS Competition (competitionId int PRIMARY KEY, competitionName varchar(255), prize int, eventId int, subEventId int)";

        t.update(x);

    }

    public void insertCompetition(Competition c) {

        String x = "INSERT INTO Competition VALUES (?, ?, ?, ?, ?)";

        t.update(x, c.getCompetitionId(), c.getCompetitionName(), c.getPrize(), c.getEventId(), c.getSubEventId());

    }

    public void deleteCompetition(int c) {

        String x = "DELETE FROM Competition WHERE competitonId = ?";

        t.update(x, c);

    }

}
