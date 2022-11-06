package com.example.fms.festmanagement.doa;

import com.example.fms.festmanagement.models.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TransactionRepo {

    @Autowired
    private JdbcTemplate t;

    public void insertTransaction(Transaction r) {

        String x = "INSERT INTO Transaction(amount,datetime,cartid) VALUES (?, ?, ?)";

        t.update(x, r.getAmount(), r.getDateTime(), r.getCartId());

    }

    public void deleteTransaction(int r) {

        String x = "DELETE FROM Transaction WHERE transactionId = ?";

        t.update(x, r);

    }

}
