package com.example.fms.festmanagement.doa;

import com.example.fms.festmanagement.models.Cart;
import com.example.fms.festmanagement.models.CartItemDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CartItemDetailsRepo {

    @Autowired
    private JdbcTemplate t;

    public void initCartItemDetails() {

        String x = "CREATE TABLE IF NOT EXISTS CartItemDetails (cartItemId INT PRIMARY KEY, quantity INT DEFAULT 0, cartId INT NOT NULL, itemId INT NOT NULL)";

        t.update(x);

    }

    public void insertCartItemDetails(CartItemDetails c) {

        String x = "INSERT INTO CartItemDetails VALUES (?, ?, ?)";

        t.update(x, c.getQuantity(), c.getCartId(), c.getItemId());

    }

    public void deleteCartItemDetails(Cart cart, int c) {

        String x = "DELETE FROM CartItemDetails WHERE cartId = ? AND itemid=?";

        t.update(x,cart.getCartId(), c);

    }

    public void updateCartItemDetails(CartItemDetails cid) {

        String x = "UPDATE CartItemDetails SET quantity = ? WHERE itemId = ? and cartId = ?";

        t.update(x, cid.getQuantity(), cid.getItemId(), cid.getCartId());

    }

    public void removeFromCart(Cart c, int itemId) {

        String x = "DELETE FROM CartItemDetails WHERE itemId = ? AND cartId = ?";

        t.update(x, itemId, c.getCartId());

    }

}
