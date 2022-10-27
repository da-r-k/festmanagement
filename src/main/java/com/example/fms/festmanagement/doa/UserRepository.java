package com.example.fms.festmanagement.doa;

import com.example.fms.festmanagement.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate t;

    public void initUser() {

        String x = "CREATE TABLE IF NOT EXISTS User (userId VARCHAR(20) NOT NULL, firstName VARCHAR(20), lastName VARCHAR(20), emailId VARCHAR(50) NOT NULL, phoneNumber CHAR(10), sex INT, college VARCHAR(50), pinCode CHAR(6), city VARCHAR(50), streetName VARCHAR(50), password VARCHAR(50), PRIMARY KEY (userId))";

        t.update(x);

    }

    public void insertUser(User user) {

        String x = "INSERT INTO User VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        t.update(x, user.getUserId(), user.getFirstName(), user.getLastName(), user.getEmailId(), user.getPhoneNumber(), user.getSex(), user.getCollege(), user.getPinCode(), user.getCity(), user.getStreetName(), user.getPassword());

    }

    public User selectUser(String userId) {

        try {

            String x = "SELECT * FROM User WHERE userId = ?";

            return t.queryForObject(x, new BeanPropertyRowMapper<>(User.class), new Object[] {userId});

        }

        catch(EmptyResultDataAccessException e) {

            return null;

        }

    }

    public void updateUser(User user) {

        String x = "UPDATE User SET firstName = ?, lastName, = ?, emailId = ?, phoneNumber = ?, sex = ?, college = ?, pinCode = ?, city = ?, streetName = ? WHERE userId = ?";

        t.update(x, user.getFirstName(), user.getLastName(), user.getEmailId(), user.getPhoneNumber(), user.getStreetName(), user.getCollege(), user.getPinCode(), user.getCity(), user.getStreetName(), user.getUserId());

    }

    public void changePassword(String userId, String password) {

        String x = "UPDATE User SET password = ? WHERE userId = ?";

        t.update(x, password, userId);

    }

    public void deleteUser(String userId) {

        String x = "DELETE FROM User WHERE userId = ?";

        t.update(x, userId);

    }

}
