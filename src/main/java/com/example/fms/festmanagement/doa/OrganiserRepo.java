package com.example.fms.festmanagement.doa;

import com.example.fms.festmanagement.models.Organiser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OrganiserRepo {

    @Autowired
    private JdbcTemplate t;

    public void insertOrganiser(Organiser o) {

        String x = "INSERT INTO Organiser(organiserEmail,firstName,lastName,mobileNumber,eventId) VALUES (?, ?, ?, ?, ?)";

        t.update(x, o.getOrganiserEmail(), o.getFirstName(), o.getLastName(), o.getMobileNumber(), o.getEventId());

    }

    public void deleteOrganiser(String o) {

        String x = "DELETE FROM Organiser WHERE organiserEmail = ?";

        t.update(x, o);

    }

}
