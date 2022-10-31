package com.example.fms.festmanagement.doa;

import com.example.fms.festmanagement.models.Participant;
import com.example.fms.festmanagement.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ParticipantRepo {

    @Autowired
    private JdbcTemplate t;

    public void initParticipant() {

        String x = "CREATE TABLE IF NOT EXISTS Participant (participantEmail varchar(64) PRIMARY KEY, firstName varchar(64), lastName varchar(64), mobileNumber varchar(10), sex varchar(64), college varchar(64), pinCode varchar(6), streetName varchar(64))";

        t.update(x);

    }

    public void insertParticipant(Participant p) {

        String x = "INSERT INTO Participant VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        t.update(x, p.getParticipantEmail(), p.getFirstName(), p.getLastName(), p.getMobileNumber(), p.getSex(), p.getCollege(), p.getPinCode(), p.getStreetName());

    }

    public Participant selectParticipant(String p) {

        try {

            String x = "SELECT * FROM Participant WHERE participantEmail = ?";

            return t.queryForObject(x, new BeanPropertyRowMapper<>(Participant.class), new Object[] { p });

        }

        catch (EmptyResultDataAccessException e) {

            return null;

        }

    }

    public void updateParticipant(Participant p) {

        String x = "UPDATE Participant SET firstName = ?, lastName = ?, mobileNumber = ?, sex = ?, college = ?, pinCode = ?, streetName = ? WHERE participantEmail = ?";

        t.update(x, p.getFirstName(), p.getLastName(), p.getMobileNumber(), p.getSex(), p.getCollege(), p.getPinCode(), p.getStreetName(), p.getParticipantEmail());

    }

    public void deleteParticipant(String p) {

        String x = "DELETE FROM Participant WHERE participantEmail = ?";

        t.update(x, p);

    }

}
