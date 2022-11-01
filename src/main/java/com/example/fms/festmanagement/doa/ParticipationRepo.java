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

        String x = "CREATE TABLE IF NOT EXISTS Participation (participationId int PRIMARY KEY, position int, participationEmail varchar(255), competitionId int)";

        t.update(x);

    }

    public void insertParticipation(Participation p) {

        String x = "INSERT INTO Participation VALUES (?, ?, ?, ?)";

        t.update(x, p.getParticipationId(), p.getPosition(), p.getParticipantEmail(), p.getCompetitionId());

    }

    public void deleteParticipation(int p) {

        String x = "DELETE FROM Participation WHERE participationId = ?";

        t.update(x, p);

    }

}
