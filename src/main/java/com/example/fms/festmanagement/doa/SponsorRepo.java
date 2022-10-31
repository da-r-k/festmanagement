package com.example.fms.festmanagement.doa;

import com.example.fms.festmanagement.models.Event;
import com.example.fms.festmanagement.models.Sponsor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SponsorRepo {

    @Autowired
    private JdbcTemplate t;

    public void initSponsor() {

        String x = "CREATE TABLE IF NOT EXISTS Sponsor (sponsorId int PRIMARY KEY, sponsorName varchar(64), amount int)";

        t.update(x);

    }

    public void insertSponsor(Sponsor s) {

        String x = "INSERT INTO Sponsor VALUES (?, ?, ?)";

        t.update(x, s.getSponsorId(), s.getSponsorName(), s.getAmount());

    }

    public Sponsor selectSponsor(int s) {

        try {

            String x = "SELECT * FROM Sponsor WHERE sponsorId = ?";

            return t.queryForObject(x, new BeanPropertyRowMapper<>(Sponsor.class), new Object[] { s });

        }

        catch (EmptyResultDataAccessException e) {

            return null;

        }

    }

    public void updateSponsor(Sponsor s) {

        String x = "UPDATE Sponsor SET amount = ? WHERE sponsorId = ?";

        t.update(x, s.getAmount(), s.getSponsorId());

    }

    public void deleteSponsor(int s) {

        String x = "DELETE FROM Sponsor WHERE sponsorId = ?";

        t.update(x, s);

    }

}
