package com.example.Controller;
import com.example.Dto.UserSignupDto;
import com.example.Dto.UserLoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    public String home(){
        return "home";
    }
    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    @RequestMapping("/signup")
    public String signup(){
        System.out.println("signup");
        return "signup";
    }
    @ResponseBody
    @PostMapping("/userLogin")
    public String userLogin(@ModelAttribute @Validated UserLoginDto userLoginDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            // There are validation errors
            return "Please enter details in proper format";
        }
        else {
            if(userLoginService.check(userLoginDto))
                return "<h2>Login Succesfully<h2>";
            else
                return "<h2>Invalid Credentials<h2>";
        }
    }
    @PostMapping(value = "/register")
    public String register(@ModelAttribute @Validated UserSignupDto userDto, Model model) {
            if(!userSignupService.insert(userDto))
                model.addAttribute("message","Username Already exist please try with different name");
            else
                model.addAttribute("message","Successfully Registered");
            return "signup";

    }
}
