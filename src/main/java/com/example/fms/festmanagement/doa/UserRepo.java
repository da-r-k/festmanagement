package com.example.fms.festmanagement.doa;

import com.example.fms.festmanagement.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepo {

    @Autowired
    private JdbcTemplate t;


    public void initUser() {

        String x = "CREATE TABLE IF NOT EXISTS Users (userEmail varchar(255) PRIMARY KEY, password varchar(255), role varchar(255))";

        t.update(x);

    }

    public void insertUser(User u) {

        String x = "INSERT INTO Users VALUES (?, ?, ?)";

        t.update(x, u.getUserEmail(), u.getPassword(), u.getRole());

    }

    public User selectUser(String u) {

        try {

            String x = "SELECT * FROM Users WHERE userEmail = ?";

            return t.queryForObject(x, new BeanPropertyRowMapper<>(User.class),u);

        }

        catch (EmptyResultDataAccessException e) {

            return null;

        }

    }

    public void deleteUser(String u) {

        String x = "DELETE FROM User WHERE userEmail = ?";

        t.update(x, u);

    }

}
