package com.example.fms.festmanagement.doa;

import com.example.fms.festmanagement.models.Fund;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FundRepo {

    @Autowired
    private JdbcTemplate t;

    public void insertFund(Fund f) {

        String x = "INSERT INTO Fund(sponsorId, eventId, amount) VALUES (?, ?, ?)";

        t.update(x, f.getSponsorId(), f.getEventId(), f.getAmount());

    }

    public void deleteFund(int f) {

        String x = "DELETE FROM Fund WHERE fundId = ?";

        t.update(x, f);

    }

}
