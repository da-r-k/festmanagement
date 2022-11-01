package com.example.fms.festmanagement.doa;

import com.example.fms.festmanagement.models.Participation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ParticipationRepo {

    @Autowired
    private JdbcTemplate t;

    public void initParticipation() {

        String x = "CREATE TABLE IF NOT EXISTS Participation (participantEmail VARCHAR(255), position INT, competitionId INT, PRIMARY KEY (participationEmail, competitionId))";

        t.update(x);

    }

    public void insertParticipation(Participation p) {

        String x = "INSERT INTO Participation VALUES (?, ?, ?)";

        t.update(x, p.getParticipantEmail(), p.getPosition(), p.getCompetitionId());

    }

    public void deleteParticipation(String p, int c) {

        String x = "DELETE FROM Participation WHERE participantEmail = ? AND competitionId = ?";

        t.update(x, p, c);

    }

}
