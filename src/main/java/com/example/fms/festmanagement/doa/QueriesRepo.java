package com.example.fms.festmanagement.doa;

import com.example.fms.festmanagement.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class QueriesRepo {

    @Autowired
    private JdbcTemplate t;

    public Cart activeCart(int u) {

        try {

            String x = "SELECT * FROM Cart NOT IN (SELECT C.cartId Cart AS C NATURAL JOIN Transaction AS T) AND userId = ?";

            return t.queryForObject(x, new BeanPropertyRowMapper<>(Cart.class),u);

        }

        catch (EmptyResultDataAccessException e) {

            return null;

        }

    }

}
