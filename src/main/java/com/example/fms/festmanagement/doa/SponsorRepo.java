package com.example.fms.festmanagement.doa;

import com.example.fms.festmanagement.models.Sponsor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SponsorRepo {

    @Autowired
    private JdbcTemplate t;

    public void insertSponsor(Sponsor s) {

        String x = "INSERT INTO Sponsor(sponsorName) VALUES (?)";

        t.update(x, s.getSponsorName());

    }


    public void deleteSponsor(int s) {

        String x = "DELETE FROM Sponsor WHERE sponsorId = ?";

        t.update(x, s);

    }

}
