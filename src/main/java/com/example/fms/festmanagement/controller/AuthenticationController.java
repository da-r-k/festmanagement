package com.example.fms.festmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.fms.festmanagement.models.LoginDetails;
import com.example.fms.festmanagement.service.AuthenticationService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/login")
    public String login(Model model, HttpSession session) {
        if (authenticationService.isAuthenticated(session)) {
            return "redirect:/";
        }

        model.addAttribute("credentials", new LoginDetails());
        return "login";
    }

    @PostMapping("/login")
    public String postLogin(@ModelAttribute LoginDetails credentials, Model model, HttpSession session,
            RedirectAttributes redirectAttributes) {
        if (authenticationService.isAuthenticated(session)) {
            return "redirect:/";
        }

        String emailId = credentials.getemailId();
        String password = credentials.getPassword();
        String message=null;

        try {
            if (authenticationService.checkCredentials(emailId, password)) {
                authenticationService.loginUser(session, emailId);

                model.addAttribute("successToast", "Successfully logged in");
                System.out.println("loggedin");
                return "redirect:/dashboard";
            }
            else{
                message="Incorrect password entered";
            }
        } catch (Exception e) {
            System.out.println(e);
            message="The Email ID does not belong to an account";
        }

        model.addAttribute("errorToast", message);
        model.addAttribute("credentials", credentials);
        System.out.println(message);
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(Model model, HttpSession session) {
        authenticationService.logoutUser(session);
        return "redirect:/";
    }

}
