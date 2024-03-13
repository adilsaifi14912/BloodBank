package com.example.Charul_BloodBank.controller;

import com.example.Charul_BloodBank.dto.UserLoginDTO;
import com.example.Charul_BloodBank.dto.UserSignUpDTO;
import com.example.Charul_BloodBank.model.UserModel;
import com.example.Charul_BloodBank.service.LoginService;
import com.example.Charul_BloodBank.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private SignUpService userSignupService;
    @Autowired
    private LoginService userLoginService;

    @RequestMapping("/")
    public String login() {
        return "login";
    }

    @RequestMapping("/signup")
    public String signup() {
        return "signup";
    }

    @PostMapping("/userLogin")
    public String userLogin(UserLoginDTO userLoginDto, Model model) {
        if (userLoginService.authenticate(userLoginDto))
        {
            UserModel user = userLoginService.getUser(userLoginDto.getUsername());
            String userRole = user.getRole();
            model.addAttribute("user", user);
            if ("admin".equals(userRole)) {
                List<UserModel> userList = userSignupService.getAllUsers();
                model.addAttribute("userList", userList);
                return "adminDashboard";
            } else if ("agent".equals(userRole)) {
                return "agentDashboard";
            } else if ("enduser".equals(userRole)) {
                return "endUserDashboard";
            } else {
                return "login";
            }
        }
        else
            return "login";
    }
    @ResponseBody
    @PostMapping("/register")
    public String register(@ModelAttribute @Validated UserSignUpDTO userDto) {
                userSignupService.insert(userDto);
        return "Registered";

    }
}