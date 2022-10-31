package com.example.fms.festmanagement.doa;

import com.example.fms.festmanagement.models.CartItemDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CartItemDetailsRepo {

    @Autowired
    private JdbcTemplate t;

    public void initCartItemDetails() {

        String x = "CREATE TABLE IF NOT EXISTS CartItemDetails (cartItemId int PRIMARY KEY, quantity int, cartId int, itemId int)";

        t.update(x);

    }

    public void insertCartItemDetails(CartItemDetails c) {

        String x = "INSERT INTO CartItemDetails VALUES (?, ?, ?, ?)";

        t.update(x, c.getCartItemId(), c.getQuantity(), c.getCartId(), c.getItemId());

    }

    public void deleteCartItemDetails(int c) {

        String x = "DELETE FROM CartItemDetails WHERE cartItemId = ?";

        t.update(x, c);

    }

}
