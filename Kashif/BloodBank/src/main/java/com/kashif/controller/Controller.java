package com.kashif.controller;

import com.kashif.service.BloodRequestService;
import com.kashif.service.UtilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private UtilityService service;

    // --- Homepage where users can go to signin/signup page ------
    @RequestMapping("/")
    public String home() {
        return "home";
    }

    @RequestMapping("/test")
    public String test() {
        return "test";
    }


}
