package com.example.fms.festmanagement.doa;

import com.example.fms.festmanagement.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepo {

    @Autowired
    private JdbcTemplate t;

    public void insertUser(User u) {

        String x = "INSERT INTO Users VALUES (?, ?, ?)";

        t.update(x, u.getUserEmail(), u.getPassword(), u.getRole());

    }

    public void deleteUser(String u) {

        String x = "DELETE FROM Users WHERE userEmail = ?";

        t.update(x, u);

    }

}
