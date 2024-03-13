package com.kashif.controller;

import com.kashif.dto.LoginDTO;
import com.kashif.dto.RegistrationDTO;
import com.kashif.entity.UserRegistration;
import com.kashif.repository.UserRepo;
import com.kashif.service.UserRegistrationService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@Controller
public class AuthenticationController {

    @Autowired
    private UserRegistrationService userRegistrationService;

    // ----- SignUp Page ----
    @GetMapping("/signup")
    public String signup() {
        return "signup";

    }

    // Users get Registered and Success Message

    @PostMapping("/registration")
    public String register(RegistrationDTO registrationDTO, Model mp) {
        if (userRegistrationService.existsByUsernamer(registrationDTO.getUsername())) {
            mp.addAttribute("errorMsg", "Username Already Exists");
            return "signup";
        }

            RegistrationDTO registeredUser = userRegistrationService.registerUser(registrationDTO);
            mp.addAttribute("success", "Account Created Successfully");
            return "login";
        
    }


    // ------ Login Page -----
    @GetMapping("/login")
    public String login() {
        return "login";
    }


    //----- Got the Login details and redirected to Dashboard -----
    @PostMapping("/login")
    public String loginChk(LoginDTO loginDTO, HttpSession session, Model model) {
        return userRegistrationService.loginCheck(loginDTO, session, model);
    }


    // ------ Password Update Page ------
    @GetMapping("/update-password")
    public String changePassword(HttpSession session) {
        return "update-password";
    }

    // ----- Updating Password and changing the newUser to False -----
    @PostMapping("/update-password")
    public String validatePassword(@RequestParam String currentPassword, @RequestParam String newPassword, @RequestParam String confirmPassword, Model mp, HttpSession session) {
        return userRegistrationService.validateLogin(currentPassword, newPassword, confirmPassword, mp, session);
    }

    //----- After redirecting to Dashboard -----
    @RequestMapping("/dashboard")
    public String dashboard(Model mp, HttpSession session) {
       return userRegistrationService.dashboard(mp, session);
    }


}
