package com.example.fms.festmanagement.doa;

import com.example.fms.festmanagement.models.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CartRepo {

    @Autowired
    private JdbcTemplate t;

    public void insertCart(Cart c) {

        String x = "INSERT INTO Cart (participantEmail) VALUES (?)";

        t.update(x, c.getParticipantEmail());

    }

}
