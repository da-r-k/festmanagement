package com.example.fms.festmanagement.doa;

import com.example.fms.festmanagement.models.Funding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FundingRepository {

    @Autowired
    private JdbcTemplate t;

    public void initFunding() {

        String x = "CREATE TABLE IF NOT EXISTS Funding (fundId INT, sponsorId INT, eventId INT, PRIMARY KEY (fundId))";

        t.update(x);

    }

    public void insertFunding(Funding funding) {

        String x = "INSERT INTO Funding VALUES (?, ?, ?)";

        t.update(x, funding.getFundId(), funding.getSponsorId(), funding.getEventId());

    }

    public void deleteFunding(int fundId) {

        String x = "DELETE FROM Funding WHERE fundId = ?";

        t.update(x, fundId);

    }

}
