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

        String x = "CREATE TABLE IF NOT EXISTS Participant (participantEmail VARCHAR(255) PRIMARY KEY, firstName VARCHAR(255), lastName VARCHAR(255), mobileNumber CHAR(10), sex VARCHAR(255), college VARCHAR(255), pinCode VARCHAR(6), streetName VARCHAR(255))";

        t.update(x);

    }

    public void insertParticipant(Participant p) {

        String x = "INSERT INTO Participant VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        t.update(x, p.getParticipantEmail(), p.getFirstName(), p.getLastName(), p.getMobileNumber(), p.getSex(), p.getCollege(), p.getPinCode(), p.getStreetName());

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
