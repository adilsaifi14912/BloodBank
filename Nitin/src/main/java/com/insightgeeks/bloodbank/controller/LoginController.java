package com.insightgeeks.bloodbank.controller;

import com.insightgeeks.bloodbank.dto.LoginDTO;
import com.insightgeeks.bloodbank.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * LoginController handles user login functionality for the Blood Bank application.
 */
@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;


    /**
     * Handles the POST request for user login ("/login").
     * Validates the login credentials provided by the user.
     * If successful, redirects to the user profile page; otherwise, returns to the login page.
     *
     * @param loginDTO      LoginDTO object containing user login credentials
     * @param bindingResult BindingResult object for handling validation errors
     * @param model         Model object for passing data to the view
     * @return String representing the name of the view to render (login or profile)
     */
    @PostMapping("/userLogin")
    public String performLogin(@ModelAttribute @Validated LoginDTO loginDTO,
                             BindingResult bindingResult, Model model)
    {
        // Check for validation errors in the loginDTO object
        if(bindingResult.hasErrors())
        {
            // If validation errors exist, add error message to the model and return to login page
            model.addAttribute("formatError",bindingResult.getFieldError().getDefaultMessage());
            return "login";
        }
        try{
                // Attempt to perform user login using the provided credentials
                if(loginService.performUserLogin(loginDTO))
                {
                    // If login is successful, redirect to the user profile page
                    return "profile";
                }
                else{
                    // If login is unsuccessful, add status message to model and return to login page
                    model.addAttribute("status","Invalid credentials");
                    return "login";
                }
            }
        catch (Exception e)
        {
            // If an exception occurs during login, add error message to the model and return to login page
            model.addAttribute("formatError", e.getMessage());
            return "login";
        }
    }
//
//    @PostMapping("/views/login")
//    public String performLoginn(@ModelAttribute @Validated LoginDTO loginDTO,
//                               BindingResult bindingResult, Model model)
//    {
//        // Check for validation errors in the loginDTO object
//        if(bindingResult.hasErrors())
//        {
//            // If validation errors exist, add error message to the model and return to login page
//            model.addAttribute("formatError",bindingResult.getFieldError().getDefaultMessage());
//            return "login";
//        }
//        try{
//            // Attempt to perform user login using the provided credentials
//            if(loginService.performUserLogin(loginDTO))
//            {
//                // If login is successful, redirect to the user profile page
//                return "profile";
//            }
//            else{
//                // If login is unsuccessful, add status message to model and return to login page
//                model.addAttribute("status","Invalid credentials");
//                return "login";
//            }
//        }
//        catch (Exception e)
//        {
//            // If an exception occurs during login, add error message to the model and return to login page
//            model.addAttribute("formatError", e.getMessage());
//            return "login";
//        }
//    }

}
