package com.example.fms.festmanagement.service;

import com.example.fms.festmanagement.doa.QueriesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fms.festmanagement.doa.UserRepo;
import com.example.fms.festmanagement.models.User;

import jakarta.servlet.http.HttpSession;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private QueriesRepo queriesRepo;
    private String SESSION_AUTH_KEY = "AUTH_USER";

    public Boolean checkCredentials(String emailId, String password) {

        User User = queriesRepo.selectUser(emailId);

        System.out.println("Checking credentials");

        System.out.println(User.toString());

        System.out.println(User.getPassword());

        return User.getPassword().equals(password);

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

        }

        catch (Exception e) {

            return null;

        }
    }

    public Boolean isAuthenticated(HttpSession session) {
        return getCurrentUser(session) != null;
    }

    public String getRole(String emailId) {

        User User = queriesRepo.selectUser(emailId);

        return User.getRole();
    }

    public void deleteUser(String organiserEmail) {
        userRepo.deleteUser(organiserEmail);
    }

}