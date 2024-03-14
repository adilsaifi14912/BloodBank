package com.kashif.controller;
import org.springframework.web.bind.annotation.*;

@org.springframework.stereotype.Controller
public class Controller {


    // --- Homepage where users can go to signin/signup page ------
    @RequestMapping("/")
    public String home(){
        return "home";
    }


}
