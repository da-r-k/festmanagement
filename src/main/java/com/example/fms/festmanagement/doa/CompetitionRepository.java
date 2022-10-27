package com.example.fms.festmanagement.doa;

import com.example.fms.festmanagement.models.Competition;
import com.example.fms.festmanagement.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CompetitionRepository {

    @Autowired
    private JdbcTemplate t;

    public void initCompetition() {

        String x = "CREATE TABLE IF NOT EXISTS Competition (competitionId INT, competitionName varchar(20), prize INT, eventId INT, subeventId INT, PRIMARY KEY (competitionId))";

        t.update(x);

    }

    public Competition selectCompetition(int competitionId) {

        try {

            String x = "SELECT * FrroM Competition WHERE competitionId = ?";

            return t.queryForObject(x, new BeanPropertyRowMapper<>(Competition.class), new Object[] {competitionId});

        }

        catch(EmptyResultDataAccessException e) {

            return null;

        }

    }

    public void insertCompetition(Competition comp) {

        String x = "INSERT INTO Competition VALUES (?, ?, ?, ?, ?)";

        t.update(x);

    }

}
