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
    private JdbcTemplate template;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void createUser(User user) {

        String x = "INSERT INTO User (userId, firstName, lastName, emailId, phoneNumber, sex, college, pinCode, city, streetName, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        template.update(x, user.getUserId(), user.getFirstName(), user.getLastName(), user.getEmailId(), user.getPhoneNumber(), user.getSex(), user.getCollege(), user.getPinCode(), user.getCity(), user.getStreetName(), user.getPassword());

    }

    public User getUser(String userId) {

        try {

            String x = "SELECT * FORM User WHERE userId = ?";

            return template.queryForObject(x, new BeanPropertyRowMapper<>(User.class), new Object[] {userId});

        }

        catch(EmptyResultDataAccessException e) {

            return null;

        }

    }

    public void updateUser(User user) {

        String x = "UPDATE User SET firstName = ?, lastName, = ?, emailId = ?, phoneNumber = ?, sex = ?, college = ?, pinCode = ?, city = ?, streetName = ?";

        template.update(x, user.getFirstName(), user.getLastName(), user.getEmailId(), user.getPhoneNumber(), user.getStreetName(), user.getCollege(), user.getPinCode(), user.getCity(), user.getStreetName());

    }

    public void changePassword(String userId, String password) {

        String x = "UPDATE User SET passowrd = ? WHERE userId = ?";

        template.update(x, password, userId);

    }

    public void deleteUser(String userId) {

        String x = "DELETE FROM User WHERE userId = ?";

        template.update(x, userId);

    }

}
