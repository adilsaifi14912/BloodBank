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
import org.springframework.web.bind.annotation.*;

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
    public String userLogin(@ModelAttribute @Valid UserLoginDto userLoginDto, Model model) {
        UserRegisterDto returnDto = loginService.checkUser(userLoginDto);

        if (returnDto != null) {
//            System.out.println(returnDto.isFirstLogin());
            if (returnDto.isFirstLogin()) {
                // Redirect to the password update page
                return "updatePasswordForm";
            }
            if (returnDto.isLocked()) {
//                System.out.println(returnDto.isLocked());
                return "loginAttemptsExceeded";
            } else {
                //switch for matching corresponding role and display view according with role
                switch (returnDto.getRole()) {
                    case "ADMIN":
                        model.addAttribute("dto", returnDto);
                        model.addAttribute("signupUsers", loginService.fetchSignedupUsers());
                        return "admin";
                    case "AGENT":
                        model.addAttribute("dto", returnDto);
                        return "user";
                    case "EndUser":
                        model.addAttribute("dto", returnDto);
                        return "user";
                    default:
                        return "loginError";

                }
            }
        }
        return "loginError";

    }

    @PostMapping(value = "/processUpdatePassword")
    public String updatePassword(@ModelAttribute @Valid UserLoginDto userLoginDto) {
        loginService.updatePassword(userLoginDto);
        return "passwordUpdateSuccess";
    }

}
