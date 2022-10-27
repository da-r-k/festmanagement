package com.example.fms.festmanagement.doa;

import com.example.fms.festmanagement.models.Cart;
import com.example.fms.festmanagement.models.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TransactionRepository {

    @Autowired
    private JdbcTemplate t;

    public void initTransaction() {

        String x = "CREATE TABLE IF NOT EXISTS Transaction (transactionId int, amount int, dateTime datetime, cartId int, PRIMARY KEY (transactionId), FOREIGN KEY(cartId) REFERENCES Cart(cartId))";

        t.update(x);

    }

    public void insertTransaction(Transaction trans) {

        String x = "INSERT INTO Transaction VALUES (?, ?, ?, ?)";

        t.update(x, trans.getTransactionId(), trans.getAmount(), trans.getDateTime(), trans.getCartId());

    }

    public Transaction selectTransaction(int transId) {

        try {

            String x = "SELECT * FROM Transaction WHERE transactionId = ?";

            return t.queryForObject(x, new BeanPropertyRowMapper<>(Transaction.class), new Object[] {transId});

        }

        catch(EmptyResultDataAccessException e) {

            return null;

        }

    }

    public void deleteTransaction(int transId) {

        String x = "DELETE FROM Transaction WHERE transactionId = ?";

        t.update(x, transId);

    }

}
