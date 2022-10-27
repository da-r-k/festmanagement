package com.example.fms.festmanagement.doa;

import com.example.fms.festmanagement.models.Sponsor;
import com.example.fms.festmanagement.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SponsorRepository {

    @Autowired
    private JdbcTemplate t;

    private void initSponsor() {

        String x = "CREATE TABLE Sponsor (sponsorId INT, sponsorName VARCHAR(20), amount INT, PRIMARY KEY (sponsorId))";

        t.update(x);

    }

    private void insertSponsor(Sponsor sponsor) {

        String x = "INSERT INTO Sponsor VALUES (?, ?, ?)";

        t.update(x);

    }

    private Sponsor selectSponsor(int sponsorId) {

        try {

            String x = "SELECT * FROM Sponsor WHERE sponsorId = ?";

            return t.queryForObject(x, new BeanPropertyRowMapper<>(Sponsor.class), new Object[] {sponsorId});

        }

        catch(EmptyResultDataAccessException e) {

            return null;

        }

    }

    private void deleteSponsor(int sponsorId) {

        String x = "DELETE FROM Sponsor WHERE sponsorId = ?";

        t.update(x, sponsorId);

    }

}
