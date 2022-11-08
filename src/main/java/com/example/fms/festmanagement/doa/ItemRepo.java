package com.example.fms.festmanagement.doa;

import com.example.fms.festmanagement.models.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ItemRepo {

    @Autowired
    private JdbcTemplate t;

    public void insertItem(Item i) {

        String x = "INSERT INTO Item (itemName,sellingPrice,costPrice,stock) VALUES ( ?, ?, ?, ?)";

        t.update(x, i.getItemName(), i.getSellingPrice(), i.getCostPrice(), i.getStock());

    }

    public void deleteItem(int i) {

        String x = "DELETE FROM Item WHERE itemId = ?";

        t.update(x, i);

    }

    public void updateItem(Item item) {

        String x = "UPDATE Item SET sellingPrice = ?, costPrice = ?, stock = ? WHERE itemId = ?";

        t.update(x, item.getSellingPrice(), item.getCostPrice(), item.getStock(), item.getItemId());

    }

}
