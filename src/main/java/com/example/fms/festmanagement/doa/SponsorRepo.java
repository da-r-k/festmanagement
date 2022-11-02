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

        String x = "CREATE TABLE IF NOT EXISTS Sponsor (sponsorId INT PRIMARY KEY, sponsorName VARCHAR(255) NOT NULL, amount INT NOT NULL)";

        t.update(x);

    }

    public void insertSponsor(Sponsor s) {

        String x = "INSERT INTO Sponsor VALUES (?, ?, ?)";

        t.update(x, s.getSponsorId(), s.getSponsorName(), s.getAmount());

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
