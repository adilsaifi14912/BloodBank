package com.insightgeeks.bloodbank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller class responsible for handling page navigation requests related to user authentication
 * and registration.
 */
@Controller
public class PageController {


    /**
     * Request mapping for displaying the signup page.
     *
     * @return The view name for the signup page.
     */
    @RequestMapping("/signup")
    public String getSignupView()
    {
        return "signup";
    }


    /**
     * Request mapping for displaying the login page.
     *
     * @return The view name for the login page.
     */
    @RequestMapping("/login")
    public String getLoginView()
    {
        return "login";
    }
}
