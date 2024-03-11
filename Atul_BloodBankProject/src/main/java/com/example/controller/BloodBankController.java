package com.example.controller;

import com.example.dto.UserLoginDto;
import com.example.dto.UserRegisterDto;
import com.example.service.AdminService;
import com.example.service.LoginService;
import com.example.service.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
public class BloodBankController {
    @Autowired
    SignupService signupService;
    @Autowired
    LoginService loginService;

    @RequestMapping("/")
    public String showHome() {
        return "home";
    }

    @RequestMapping("/signup")
    public String signUp() {
        return "signup";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping(value = "/userRegister")
    public String userRegister(@ModelAttribute @Valid UserRegisterDto userRegisterDto, Model model) {
        if (signupService.addUser(userRegisterDto)) {
            model.addAttribute("successMessage", "UserName already exits ,Please try again with different username");
        } else
            model.addAttribute("successMessage", "Successfully registered!");
        return "signup";
    }

    @PostMapping(value = "/userLogin")
    public String userLogin(@ModelAttribute @Valid UserLoginDto userLoginDto) {
        if (loginService.checkUser(userLoginDto)) {
            return "loginExist";
        } else {
            return "loginError";
        }

    }

}
