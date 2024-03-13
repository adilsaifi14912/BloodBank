package com.insightgeeks.bloodbank.controller;

import com.insightgeeks.bloodbank.dto.LoginDTO;
import com.insightgeeks.bloodbank.dto.PasswordResetDTO;
import com.insightgeeks.bloodbank.dto.SignupDTO;
import com.insightgeeks.bloodbank.service.DatabaseSetupService;
import com.insightgeeks.bloodbank.service.LoginService;
import com.insightgeeks.bloodbank.service.SignupService;
import com.insightgeeks.bloodbank.util.LoginResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthenticationController {

    static int loginAttempts = 0; // Static variable to track login attempts

    // Autowired services and DTOs
    @Autowired
    DatabaseSetupService databaseSetupService;
    @Autowired
    private SignupService signupService;
    @Autowired
    private LoginService loginService;
    @Autowired
    SignupDTO user;

    // Home mapping to initialize database and redirect to signup page
    @RequestMapping("/")
    public String home() {
        databaseSetupService.setupDatabase(); // Initialize the database
        return "login"; // Redirect to the signup page
    }

    // Mapping to get signup view
    @RequestMapping("/signup")
    public String getSignupView() {
        return "signup";
    }

    // Mapping to get login view
    @RequestMapping("/login")
    public String getLoginView() {
        return "login";
    }

    // Handling user signup
    @PostMapping("/userSignup")
    public String performSignup(@ModelAttribute @Validated SignupDTO signupDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            // If validation errors exist, return to the signup page
            model.addAttribute("formatError", bindingResult.getFieldError().getDefaultMessage());
            return "signup";
        }
        try {
            // Attempt to sign up the user
            signupService.signupUser(signupDTO);
            return "login"; // If signup is successful, redirect to the login page
        } catch (Exception e) {
            // If an exception occurs during signup, return to the signup page with error message
            model.addAttribute("formatError", e.getMessage());
            return "signup";
        }
    }

    // Handling user login
    @PostMapping("/userLogin")
    public String performLogin(@ModelAttribute @Validated LoginDTO loginDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("formatError", bindingResult.getFieldError().getDefaultMessage());
            return "login"; // If validation errors exist, return to the login page
        }

        try {
            // Attempt user login
            LoginResult loginResult = loginService.performUserLogin(loginDTO);
            user = loginResult.getUser();

            // Process login result
            switch (loginResult.getStatus()) {
                case "success":
                    // Handle successful login
                    if (user.getBlockStatus().equalsIgnoreCase("blocked")) {
                        model.addAttribute("blockStatus", "User Blocked");
                        return "login";
                    }

                    switch (loginResult.getUser().getRole()) {
                        // Redirect to appropriate profile page based on user role
                        case "admin":
                            model.addAttribute("user", loginResult.getUser());
                            model.addAttribute("signedupUsers", loginService.fetchSignedupUsers());
                            return "adminProfilePage";

                        case "agent":
                            model.addAttribute("user", loginResult.getUser());
                            return "agentProfilePage";

                        case "user":
                            model.addAttribute("user", loginResult.getUser());
                            return "endUserProfilePage";
                    }

                case "reset":
                    // Redirect to password reset page if password reset is required
                    model.addAttribute("username", loginResult.getUser().getUsername());
                    return "passwordReset";

                case "invalid":
                    // Handle invalid credentials
                    model.addAttribute("status", "Invalid credentials");
                    if (loginResult.getBlockStatus() == 1) {
                        model.addAttribute("blockStatus", "User Blocked");
                    } else if (loginResult.getBlockStatus() == 2) {
                        model.addAttribute("blockStatus", "User cannot be blocked because user does not exist");
                    }
                    return "login";
            }
        } catch (Exception e) {
            // If an exception occurs, return to the login page with error message
            model.addAttribute("formatError", e.getMessage());
            return "login";
        }
        return "";
    }

    // Handling password change request
    @PostMapping(value = "/changePassword")
    public String changePassword(@ModelAttribute @Validated PasswordResetDTO passwordResetDTO, Model model,
                                 BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            // If validation errors exist, return to the password reset page
            model.addAttribute("formarError", "Please fill all fields properly");
            return "passwordReset";
        }

        // Process password reset
        switch (loginService.updatePassword(user, passwordResetDTO)) {
            case "resetSuccess":
                // Password reset successful
                model.addAttribute("passwordResetStatus", "Password successfully changed");
                return "login";

            case "unmatchedPassword":
                // Unmatched passwords
                model.addAttribute("passwordResetStatus", "Passwords do not match");
                return "passwordReset";

            case "missingUser":
                // User does not exist
                model.addAttribute("passwordResetStatus", "User does not exist");
                return "passwordReset";
        }
        return "";
    }
}
