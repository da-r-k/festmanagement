package com.example.fms.festmanagement.doa;

import com.example.fms.festmanagement.models.Cart;
import com.example.fms.festmanagement.models.CartItemDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CartItemDetailsRepository {

    @Autowired
    private JdbcTemplate t;

    public void initCid() {

        String x = "CREATE TABLE IF NOT EXISTS CardTransactionDetails (cidId INT, quantity INT, cartId INT, itemId INT, PRIMARY KEY (cidId))";

        t.update(x);

    }

    public void insertCid(CartItemDetails cid) {

        String x = "INSERT INTO CartItemDetails VALUES (?, ?, ?, ?)";

        t.update(x, cid.getCidId(), cid.getQuantity(), cid.getCartId(), cid.getItemId());

    }

    public CartItemDetails selectCid(int cidId) {

        try {

            String x = "SELECT * FROM CartItemDetails WHERE cidId = ?";

            return t.queryForObject(x, new BeanPropertyRowMapper<>(CartItemDetails.class), new Object[] {cidId});

        }

        catch(EmptyResultDataAccessException e) {

            return null;

        }

    }

    public void deleteCid(int cidId) {

        String x = "DELETE FROM CartItemDetails WHERE cidId = ?";

        t.update(x, cidId);

    }

}
