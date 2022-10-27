package com.example.fms.festmanagement.doa;

import com.example.fms.festmanagement.models.Cart;
import com.example.fms.festmanagement.models.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CartRepository {

    @Autowired
    private JdbcTemplate t;

    public void cartInit() {

        String x = "CREATE TABLE IF NOT EXISTS Cart (cartId INT, userId VARCHAR(20), PRIMARY KEY(cardId))";

        t.update(x);

    }

    public void insertCart(Cart cart) {

        String x = "INSERT INTO Cart VALUES (?, ?)";

        t.update(x, cart.getCartId(), cart.getUserId());

    }

    public Cart selectCart(int cartId) {

        try {

            String x = "SELECT * FROM Cart WHERE cartId = ?";

            return t.queryForObject(x, new BeanPropertyRowMapper<>(Cart.class), new Object[] {cartId});

        }

        catch(EmptyResultDataAccessException e) {

            return null;

        }

    }

    public void deleteCart(int cartId) {

        String x = "DELETE FROM Cart WHERE cartId = ?";

        t.update(x, cartId);

    }

}
