package com.example.fms.festmanagement.doa;

import com.example.fms.festmanagement.models.Participation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ParticipationRepo {

    @Autowired
    private JdbcTemplate t;

    public void insertParticipation(Participation p) {

        String x = "INSERT INTO Participation(participantEmail,leaderboardposition,competitionId,subEventId,eventId) VALUES (?, ?, ?, ?, ?)";

        t.update(x, p.getParticipantEmail(), p.getLeaderBoardPosition(), p.getCompetitionId(), p.getSubEventId(), p.getEventId());

    }

    public void deleteParticipation(String p, int c, int s, int e) {

        String x = "DELETE FROM Participation WHERE participantEmail = ? AND competitionId = ? AND subEventId = ? AND eventId = ?";

        t.update(x, p, c, s, e);

    }

    public void updateParticipation(Participation p) {

        String x = "UPDATE Participation SET leaderBoardPosition = ? WHERE participantEmail = ? AND competitionId = ? AND subEventId = ? AND eventId = ?";
        System.out.println(p.toString());

        t.update(x, p.getLeaderBoardPosition(), p.getParticipantEmail(), p.getCompetitionId(), p.getSubEventId(), p.getEventId());

    }

}
