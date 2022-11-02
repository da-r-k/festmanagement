package com.example.fms.festmanagement.doa;

import com.example.fms.festmanagement.models.Organiser;
import com.example.fms.festmanagement.models.Participant;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OrganiserRepo {

    @Autowired
    private JdbcTemplate t;

    public void initOrganiser() {

        String x = "CREATE TABLE IF NOT EXISTS Organiser (organiserEmail VARCHAR(255) PRIMARY KEY, fistName VARCHAR(255), lastName VARCHAR(255), mobileNumber CHAR(10), eventId INT)";

        t.update(x);

    }

    public void insertOrganiser(Organiser o) {

        String x = "INSERT INTO Organiser VALUES (?, ?, ?, ?, ?)";

        t.update(x, o.getOrganiserEmail(), o.getFirstName(), o.getLastName(), o.getMobileNumber(), o.getEventId());

    }

    public void deleteOrganiser(String o) {

        String x = "DELETE FROM Organiser WHERE organiserEmail = ?";

        t.update(x, o);

    }

}
