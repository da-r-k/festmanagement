package com.example.fms.festmanagement.doa;

import com.example.fms.festmanagement.models.Organiser;
import com.example.fms.festmanagement.models.Participant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OrganiserRepo {

    @Autowired
    private JdbcTemplate t;

    private void init() {

        String x = "CREATE TABLE IF NOT EXISTS Organiser (organiserEmail varchar(64) PRIMARY KEY, fistName varchar(64), lastName varchar(64), mobileNumber varchar(10), eventId int)";

        t.update(x);

    }

    private void insertOrganiser(Organiser o) {

        String x = "INSERT INTO Organiser VALUES (?, ?, ?, ?, ?)";

        t.update(x, o.getOrganiserEmail(), o.getFirstName(), o.getLastName(), o.getMobileNumber(), o.getEventId());

    }

    private Organiser selectOrganiser(String o) {

        try {

            String x = "SELECT * FROM Organiser WHERE organiserEmail = ?";

            return t.queryForObject(x, new BeanPropertyRowMapper<>(Organiser.class), new Object[] { o });

        }

        catch (EmptyResultDataAccessException e) {

            return null;

        }

    }

    private void deleteOrganiser(String o) {

        String x = "DELETE FROM Organiser WHERE organiserEmail = ?";

        t.update(x, o);

    }

}
