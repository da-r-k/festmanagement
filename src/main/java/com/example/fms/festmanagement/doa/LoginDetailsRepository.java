package com.example.fms.festmanagement.doa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.fms.festmanagement.models.LoginDetails;

@Repository
public class LoginDetailsRepository {

    @Autowired
    private JdbcTemplate t;

    public void initLoginDetails(){

        String x= "CREATE TABLE IF NOT EXISTS LoginDetails (emailId VARCHAR(50) NOT NULL,password VARCHAR(50) NOT NULL, role VARCHAR(50) NOT NULL, enabled BOOLEAN, PRIMARY KEY (emailId))";

        t.update(x);
    }

    public LoginDetails getLoginDetails(String emailId){

        String x= "SELECT * FROM LoginDetails WHERE emailId = ?";

        return t.queryForObject(x,new BeanPropertyRowMapper<>(LoginDetails.class),emailId);
    }
    
    public void createLoginDetails(String emailId, String password, String role) {

        String x = "INSERT INTO LOGINDETAILS (emailId, password, role, enabled) VALUES (?,?,?,1)";

        t.update(x, emailId, password, role);
    }

}
