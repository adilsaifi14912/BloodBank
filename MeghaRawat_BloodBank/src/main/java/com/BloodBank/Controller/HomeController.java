package com.BloodBank.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String homePage(){
        return "homePage";
    }

    @RequestMapping("/signup")
    public String signUp() {
        return "signUp";
    }



}
