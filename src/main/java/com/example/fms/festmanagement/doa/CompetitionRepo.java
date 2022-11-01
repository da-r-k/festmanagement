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

        String x = "CREATE TABLE IF NOT EXISTS Competition (competitionId INT, competitionName VARCHAR(255) NOT NULL, prize INT DEFAULT 0, eventId INT NOT NULL, subEventId INT NOT NULL, PRIMARY KEY (competitionId, eventId, subEventId))";

        t.update(x);

    }

    public void insertCompetition(Competition c) {

        String x = "INSERT INTO Competition VALUES (?, ?, ?, ?, ?)";

        t.update(x, c.getCompetitionId(), c.getCompetitionName(), c.getPrize(), c.getEventId(), c.getSubEventId());

    }

    public void deleteCompetition(int c, int s, int e) {

        String x = "DELETE FROM Competition WHERE competitionId = ? AND subEventId = ? AND eventId = ?";

        t.update(x, c, s, e);

    }

}
