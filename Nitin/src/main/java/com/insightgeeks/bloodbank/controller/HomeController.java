package com.insightgeeks.bloodbank.controller;

import com.insightgeeks.bloodbank.service.DatabaseSetupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * HomeController handles the request related to the home page of the Blood Bank application.
 */

@Controller
public class HomeController {

    @Autowired
    DatabaseSetupService databaseSetupService;

    /**
     * Handles the request mapping for the home page ("/").
     * This method sets up the database and redirects the user to the signup page.
     *
     * @return String representing the name of the view to render (signup.jsp)
     */
    @RequestMapping("/")
    public String home()
    {
        // Call the setupDatabase method of the DatabaseSetupService
        // to initialize the database for the Blood Bank application.
        databaseSetupService.setupDatabase();

        // Redirect the user to the signup page after database setup.
        return "login";
    }
}
