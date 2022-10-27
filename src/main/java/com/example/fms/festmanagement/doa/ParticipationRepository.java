package com.example.fms.festmanagement.doa;

import com.example.fms.festmanagement.models.Participation;
import com.example.fms.festmanagement.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ParticipationRepository {

    @Autowired
    private JdbcTemplate t;

    public void initParticipation() {

        String x = "CREATE TABLE IF NOT EXISTS Participation (participationId INT, position INT, userId INT, competitionId INT, PRIMARY KEY (participationId))";

        t.update(x);

    }

    public void insertParticipation(Participation part) {

        String x = "INSERT INTO Participation VALUES (?, ?, ?, ?)";

        t.update(x, part.getParticipationId(), part.getPosition(), part.getUserId(), part.getCompetitionId());

    }

    public Participation selectParticipation(int participationId) {

        try {

            String x = "SELECT * FROM Participation WHERE participationId = ?";

            return t.queryForObject(x, new BeanPropertyRowMapper<>(Participation.class), new Object[] {participationId});

        }

        catch(EmptyResultDataAccessException e) {

            return null;

        }

    }

    public void deleteParticipation(int participationId) {

        String x = "DELETE FROM Participation WHERE participationId = ?";

        t.update(x, participationId);

    }

}
