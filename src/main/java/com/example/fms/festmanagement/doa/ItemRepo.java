package com.example.fms.festmanagement.doa;

import com.example.fms.festmanagement.models.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ItemRepo {

    @Autowired
    private JdbcTemplate t;

    public void initItem() {

        String x = "CREATE TABLE IF NOT EXISTS Item (itemId int PRIMARY KEY, itemName varchar(255), price int, stock int)";

        t.update(x);

    }

    public void insertItem(Item i) {

        String x = "INSERT INTO Item VALUES (?, ?, ?, ?)";

        t.update(x, i.getItemId(), i.getItemName(), i.getPrice(), i.getStock());

    }

    public void deleteItem(int i) {

        String x = "DELETE FROM Item WHERE itemId = ?";

        t.update(x, i);

    }

}
