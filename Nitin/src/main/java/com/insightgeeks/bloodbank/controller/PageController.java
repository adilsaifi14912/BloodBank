package com.insightgeeks.bloodbank.controller;

import com.insightgeeks.bloodbank.service.AdminService;
import com.insightgeeks.bloodbank.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class PageController {

    @Autowired
    AdminService adminService;

    @Autowired
    AgentService agentService;

    // Mapping to get signup view
    @RequestMapping("/signup")
    public String getSignupView() {
        return "signup";
    }

    // Mapping to get login view
    @RequestMapping("/login")
    public String getLoginView() {
        return "login";
    }

    @RequestMapping("/createAgent")
    public String createAgent()
    {
        return "createAgent";
    }

    @RequestMapping("/createUser")
    public String createUser(){ return "createUser"; }

    @RequestMapping("/signupUsers")
    public String signupUsers(){ return "signupUsersDetails"; }

    @RequestMapping("/admin")
    public String returnToAdminPage(){ return "adminProfilePage";}

    @RequestMapping("/agent")
    public String returnToAgentPage(){ return "agentProfilePage";}

    @GetMapping("/user")
    public String returnToUserPage(){ return "endUserProfilePage";}


    @RequestMapping("/bloodRequirement")
    public String bloodRequirementForm()
    {
        return "bloodRequest";
    }

}
