package com.example.fms.festmanagement.doa;

import com.example.fms.festmanagement.models.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CartRepo {

    @Autowired
    private JdbcTemplate t;

    public void initCart() {

        String x = "CREATE TABLE IF NOT EXISTS Cart (cartId int PRIMARY KEY, participantEmail varchar(255))";

        t.update(x);

    }

    public void insertCart(Cart c) {

        String x = "INSERT INTO Cart VALUES (?, ?)";

        t.update(x, c.getCartId(), c.getParticipantEmail());

    }

    public void deleteCart(int c) {

        String x = "DELETE FROM Cart WHERE cartId = ?";

        t.update(x, c);

    }

}
