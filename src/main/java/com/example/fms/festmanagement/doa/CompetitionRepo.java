package com.example.fms.festmanagement.doa;

import com.example.fms.festmanagement.models.Competition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CompetitionRepo {

    @Autowired
    private JdbcTemplate t;

    public void insertCompetition(Competition c) {

        String x = "INSERT INTO Competition(competitionName, prize, eventId, SubEventId) VALUES (?, ?, ?, ?)";

        t.update(x, c.getCompetitionName(), c.getPrize(), c.getEventId(), c.getSubEventId());

    }

    public void deleteCompetition(int c, int s, int e) {

        String x = "DELETE FROM Competition WHERE competitionId = ? AND subEventId = ? AND eventId = ?";

        t.update(x, c, s, e);

    }

}
