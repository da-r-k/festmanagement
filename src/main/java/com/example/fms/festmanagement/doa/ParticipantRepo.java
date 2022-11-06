package com.example.fms.festmanagement.doa;

import com.example.fms.festmanagement.models.Participant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ParticipantRepo {

    @Autowired
    private JdbcTemplate t;

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
