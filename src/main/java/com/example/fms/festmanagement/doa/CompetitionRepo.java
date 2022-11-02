package com.example.fms.festmanagement.doa;

import com.example.fms.festmanagement.models.Competition;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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

    public List<Competition> getCompetitions(int i, int j) {

        String x = "SELECT * FROM Competition WHERE subEventId = ? AND EventId = ?";

        return t.query(x, new BeanPropertyRowMapper<>(Competition.class),i,j);

    }
    

}
