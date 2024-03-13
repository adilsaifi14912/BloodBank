package com.example.Controller;

import com.example.Dto.UserSignupDto;
import com.example.Dto.UserLoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.example.Service.*;

@Controller
public class BloodBankController {
    @Autowired
    private SignupService userSignupService;
    @Autowired
    private UserLoginService userLoginService;

    @RequestMapping("/")
    public String login() {
        return "login";
    }

    @RequestMapping("/signup")
    public String signup() {
        return "signup";
    }
    @PostMapping("/updatePassword")
    public String updatePassword(@ModelAttribute UserLoginDto userLoginDto) {
        //to update password on first login
        userLoginService.updatePassword(userLoginDto);
        return "updatedMessage";
    }

    @PostMapping("/userLogin")
    public String userLogin(@ModelAttribute @Validated UserLoginDto userLoginDto, Model model) {
        UserSignupDto user = userLoginService.check(userLoginDto);
        if (user != null) {
            System.out.println(user.isFirstLogin());
            //if user login first time then show update password page
            if(user.isFirstLogin()){
                return "updatePassword";
            }
            //if user exceed maximum limit of wrong attempt then locked page shown
            if(user.isLockStatus()){
                return "lock";
            }
            else {
                //check for user role to display their respective dashboard
                switch (user.getRole().toLowerCase()) {
                    case "enduser":
                        System.out.println("else");
                        model.addAttribute("dto", user);
                        return "user";
                    case "agent":
                        model.addAttribute("dto", user);
                        return "user";
                    case "admin":
                        model.addAttribute("dto", user);
                        model.addAttribute("list", userLoginService.signupUsers());
                        return "admin";
                    default:
                        return "errorInLogin";
                }
            }

        }
        else {
            System.out.println("else");
            return "errorInLogin";
        }

    }

    @PostMapping(value = "/register")
    public String register(@ModelAttribute @Validated UserSignupDto userDto, Model model) {
        if (!userSignupService.insert(userDto))
            model.addAttribute("message", "Username Already exist please try again with different name");
        else
            model.addAttribute("message", "Successfully Registered!!");
        return "signup";

    }
}

