package com.example.fms.festmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fms.festmanagement.doa.LoginDetailsRepository;
import com.example.fms.festmanagement.models.LoginDetails;

import jakarta.servlet.http.HttpSession;

@Service
public class AuthenticationService {

    @Autowired
    private LoginDetailsRepository logindetails;
    private String SESSION_AUTH_KEY = "AUTH_USER";

    public Boolean checkCredentials(String emailId, String password) {
        LoginDetails logindetail = logindetails.getLoginDetails(emailId);
        System.out.println("Checking credentials");
        System.out.println(logindetail.toString());
        System.out.println(logindetail.getPassword());
        return logindetail.getPassword().equals(password);
    }

    public void loginUser(HttpSession session, String emailId) {
        session.setAttribute(SESSION_AUTH_KEY, emailId);
    }

    public void logoutUser(HttpSession session) {
        session.removeAttribute(SESSION_AUTH_KEY);
    }

    public String getCurrentUser(HttpSession session) {
        try {
            return session.getAttribute(SESSION_AUTH_KEY).toString();
        } catch (Exception e) {
            return null;
        }
    }

    public Boolean isAuthenticated(HttpSession session) {
        return getCurrentUser(session) != null;
    }

    public String getRole(String emailId) {
        LoginDetails loginDetails=logindetails.getLoginDetails(emailId);
        return loginDetails.getRole();
    }

}
