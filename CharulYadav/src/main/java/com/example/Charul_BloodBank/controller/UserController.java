package com.example.Charul_BloodBank.controller;

import com.example.Charul_BloodBank.dto.UserDTO;
import com.example.Charul_BloodBank.model.User;
import com.example.Charul_BloodBank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String showSignUpForm(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "signup";
    }

    @PostMapping("/signup")
    public String signUp(@ModelAttribute("userDTO") UserDTO userDTO) {
        userService.saveUser(userDTO);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        User user = userService.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return "redirect:/success";
        } else {
            return "redirect:/login?error";
        }
    }

    @GetMapping("/success")
    public String showSuccessPage() {
        return "success";
    }
}

