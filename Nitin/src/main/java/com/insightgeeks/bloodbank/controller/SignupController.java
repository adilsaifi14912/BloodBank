package com.insightgeeks.bloodbank.controller;

import com.insightgeeks.bloodbank.dto.SignupDTO;
import com.insightgeeks.bloodbank.service.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * SignupController handles user signup functionality for the Blood Bank application.
 */
@Controller
public class SignupController {

    @Autowired
    private SignupService signupService;

    /**
     * Handles the POST request for user signup ("/signup").
     * Validates the signup information provided by the user.
     * If successful, redirects to the login page; otherwise, returns to the signup page.
     *
     * @param signupDTO     SignupDTO object containing user signup information
     * @param bindingResult BindingResult object for handling validation errors
     * @param model         Model object for passing data to the view
     * @return String representing the name of the view to render (signup or login)
     */
    @PostMapping("/userSignup")
    public String performSignup(@ModelAttribute @Validated SignupDTO signupDTO, BindingResult bindingResult, Model model)
    {
        // Check for validation errors in the signupDTO object
        if(bindingResult.hasErrors())
        {
            // If validation errors exist, add error message to the model and return to signup page
            model.addAttribute("formatError",bindingResult.getFieldError().getDefaultMessage());
            return "signup";
        }
        try{
            // Attempt to sign up the user using the provided signup information
            signupService.signupUser(signupDTO);
            // If signup is successful, redirect to the login page
            return "login";
        }
        catch (Exception e)
        {
            // If an exception occurs during signup, add error message to the model and return to signup page
            model.addAttribute("formatError", e.getMessage());
            return "signup";
        }

    }

//    @PostMapping("/views/signup")
//    public String performSignUp(@ModelAttribute @Validated SignupDTO signupDTO, BindingResult bindingResult, Model model)
//    {
//        // Check for validation errors in the signupDTO object
//        if(bindingResult.hasErrors())
//        {
//            // If validation errors exist, add error message to the model and return to signup page
//            model.addAttribute("formatError",bindingResult.getFieldError().getDefaultMessage());
//            return "signup";
//        }
//        try{
//            // Attempt to sign up the user using the provided signup information
//            signupService.signupUser(signupDTO);
//            // If signup is successful, redirect to the login page
//            return "login";
//        }
//        catch (Exception e)
//        {
//            // If an exception occurs during signup, add error message to the model and return to signup page
//            model.addAttribute("formatError", e.getMessage());
//            return "signup";
//        }
//
//    }

}
