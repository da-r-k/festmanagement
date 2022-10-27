package com.example.fms.festmanagement.doa;

import com.example.fms.festmanagement.models.Item;
import com.example.fms.festmanagement.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ItemRepository {

    @Autowired
    private JdbcTemplate t;

    public void initItem() {

        String x = "CREATE TABLE Item (itemId int, itemName varchar(50), price int, quantity int, PRIMARY KEY (itemId))";

        t.update(x);

    }

    public void createItem(Item item) {

        String x = "INSERT INTO Item VALUES (?, ?, ?, ?)";

        t.update(x, item.getItemId(), item.getItemName(), item.getPrice(), item.getQuantity());

    }

    public Item getItem(int itemId) {

        try {

            String x = "SELECT * FROM Item WHERE itemId = ?";

            return t.queryForObject(x, new BeanPropertyRowMapper<>(Item.class), new Object[] {itemId});

        }

        catch(EmptyResultDataAccessException e) {

            return null;

        }

    }

    public void updateItem(Item item) {

        String x = "UPDATE Item SET quantity = ? WHERE itemId = ?";

        t.update(x, item.getQuantity(), item.getItemId());

    }

    public void deleteItem(int itemId) {

        String x = "DELETE FROM Item WHERE itemId = ?";

        t.update(x, itemId);

    }

}
