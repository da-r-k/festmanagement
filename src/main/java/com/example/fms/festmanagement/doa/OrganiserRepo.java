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

    public Organiser selectOrganiser(String o) {

        try {

            String x = "SELECT * FROM Organiser WHERE organiserEmail = ?";

            return t.queryForObject(x, new BeanPropertyRowMapper<>(Organiser.class), new Object[] { o });

        }

        catch (EmptyResultDataAccessException e) {

            return null;

        }

    }

    public void deleteOrganiser(String o) {

        String x = "DELETE FROM Organiser WHERE organiserEmail = ?";

        t.update(x, o);

    }

    public List<Organiser> getOrganiserByEvent(int i){

        String x="SELECT * FROM Organiser WHERE eventId =?";

        return t.query(x, new BeanPropertyRowMapper<>(Organiser.class), i);
        
    }

}
