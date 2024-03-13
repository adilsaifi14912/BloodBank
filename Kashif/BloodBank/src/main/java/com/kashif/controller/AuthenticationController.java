package com.kashif.controller;

import com.kashif.dto.LoginDTO;
import com.kashif.dto.RegistrationDTO;
import com.kashif.entity.UserRegistration;
import com.kashif.repository.UserRepo;
import com.kashif.service.UserRegistrationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@Controller
public class AuthenticationController {
    private int loginAttempts = 0;
    @Autowired
    UserRepo userRepo;
    @Autowired
    private UserRegistrationService userRegistrationService;

    // ----- SignUp Page ----
    @GetMapping("/signup")
    public String signup() {
        return "signup";

    }

    // Users get Registered and Success Message

    @PostMapping(value = "/registration")
    public String register(RegistrationDTO registrationDTO, Model mp) {
        if (userRepo.existsByUsername(registrationDTO.getUsername())) {
            mp.addAttribute("errorMsg", "Username Already Exists");
            return "signup";
        }
        try {
            RegistrationDTO registeredUser = userRegistrationService.registerUser(registrationDTO);

            mp.addAttribute("success", "Account Created Successfully");
            return "login";
        } catch (Exception ex) {
            mp.addAttribute("errorMsg", ex);
            return "error";
        }
    }


    // ------ Login Page -----
    @GetMapping("/login")
    public String login() {
        return "login";
    }


    //----- Got the Login details and redirected to Dashboard -----
    @PostMapping("/login")
    public String loginChk(LoginDTO loginDTO, HttpSession session, Model model) {

        session.setAttribute("loginAttempts", loginAttempts);

        String username = loginDTO.getUsername();
        String password = loginDTO.getPassword();
        if (username==null || password==null || username.isEmpty() || password.isEmpty()) {

            model.addAttribute("errorMsg", "Login failed: Invalid Username ");
            return "login";
        }
        if (!userRepo.existsByUsername(username)) {
            model.addAttribute("errorMsg", "Login failed: Invalid Username ");
            return "login";
        }
        if (userRepo.getBlockedStatusByUsername(username)) {
            model.addAttribute("errorMsg", "User BLOCKED ");
            return "login";
        }

        String pass = userRepo.getPasswordByUsername(username);
        if (pass.equals(password)) {
            session.setAttribute("data", userRepo.findByUsername(username).get());
            return "redirect:/dashboard";
        } else {
            loginAttempts++;
        }

        if (loginAttempts > 3) {
            userRepo.updateBlockedStatusByUsername(true, username);
            session.invalidate(); // Invalidate the session to clear cache
            model.addAttribute("errorMsg", "Login failed: User BLOCKED ");

            return "login";
        }
        model.addAttribute("errorMsg", "Login failed: Invalid Password ");
        model.addAttribute("times", loginAttempts);
        return "login";
    }


    // ------ Password Update Page ------
    @GetMapping("/update-password")
    public String changePassword(HttpSession session) {
        return "update-password";
    }

    // ----- Updating Password and changing the newUser to False -----
    @PostMapping("/update-password")
    public String validatePassword(@RequestParam String currentPassword, @RequestParam String newPassword, @RequestParam String confirmPassword, Model mp, HttpSession session) {
        UserRegistration userData = (UserRegistration) session.getAttribute("data");
        if (!newPassword.equals(confirmPassword)) {
            mp.addAttribute("errorMsg", "Password didn't Match.");
            return "update-password";
        }
        if (currentPassword.equals(newPassword)) {
            mp.addAttribute("errorMsg", "Can't set old password");
            return "update-password";
        }
        if (!currentPassword.equals(userData.getPassword())) {
            mp.addAttribute("errorMsg", "Wrong Password");
            return "update-password";
        }

        String username = userData.getUsername();
        System.out.println(userData.getUsername());
        //---------- Updated the Password ---------
        userRepo.updatePasswordByUsername(newPassword, username);
        userRepo.updateNewUserByUsername(false, username);
        mp.addAttribute("success", "Password updated successfully.");
        return "login";
    }

    //----- After redirecting to Dashboard -----
    @RequestMapping("/dashboard")
    public String dashboard(Model mp, HttpSession session) {
        UserRegistration userData = (UserRegistration) session.getAttribute("data");
        mp.addAttribute("data", userData);
        if (userData.isNewUser()) {
            return "redirect:/update-password";
        }
        if (userData.getRole().equalsIgnoreCase("admin")) {

            mp.addAttribute("allUsers", userRepo.findAllEndUsers());
            return "admin-dashboard";
        }
        if (userData.getRole().equals("agent")) return "agent-dashboard";
        return "enduser-dashboard";
    }


}
