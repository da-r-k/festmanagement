package com.example.fms.festmanagement.doa;

import com.example.fms.festmanagement.models.Fund;
import com.example.fms.festmanagement.models.Sponsor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FundRepo {

    @Autowired
    private JdbcTemplate t;

    public void initFund() {

        String x = "CREATE TABLE IF NOT EXISTS Fund (fundId int PRIMARY KEY, sponsorId int, eventId int)";

        t.update(x);

    }

    public void insertFund(Fund f) {

        String x = "INSERT INTO Fund VALUES (?, ?, ?)";

        t.update(x, f.getFundId(), f.getSponsorId(), f.getEventId());

    }

    public Fund selectFund(int f) {

        try {

            String x = "SELECT * FROM Fund WHERE fundId = ?";

            return t.queryForObject(x, new BeanPropertyRowMapper<>(Fund.class), new Object[] { f });

        }

        catch (EmptyResultDataAccessException e) {

            return null;

        }

    }

    public void deleteFund(int f) {

        String x = "DELETE FROM Fund WHERE fundId = ?";

        t.update(x, f);

    }

}
