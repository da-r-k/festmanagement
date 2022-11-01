package com.example.fms.festmanagement.doa;

import com.example.fms.festmanagement.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepo {

    @Autowired
    private JdbcTemplate t;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public void initUser() {

        String x = "CREATE TABLE IF NOT EXISTS User (userEmail varchar(255) PRIMARY KEY, password varchar(255), role varchar(255))";

        t.update(x);

    }

    public void insertUser(User u) {

        u.setPassword(passwordEncoder.encode(u.getPassword()));

        String x = "INSERT INTO User VALUES (?, ?, ?)";

        t.update(x, u.getUserEmail(), u.getPassword(), u.getRole());

    }

    public User selectUser(String u) {

        try {

            String x = "SELECT * FROM User WHERE userEmail = ?";

            return t.queryForObject(x, new BeanPropertyRowMapper<>(User.class), new Object[] { u });

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
