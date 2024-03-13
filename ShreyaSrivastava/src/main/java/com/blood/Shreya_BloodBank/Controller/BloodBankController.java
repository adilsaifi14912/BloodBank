package com.blood.Shreya_BloodBank.Controller;

import com.blood.Shreya_BloodBank.Entity.UserLoginDetails;
import com.blood.Shreya_BloodBank.Entity.UserModel;
import com.blood.Shreya_BloodBank.Entity.UserSignUpDetails;
import com.blood.Shreya_BloodBank.Repository.UserRepository;
import com.blood.Shreya_BloodBank.Service.BloodBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class BloodBankController {
    @Autowired
    private BloodBankService bloodBankService;

    @GetMapping("/")
    public String welcome() {

        //System.out.println("in welcome");
        return "welcome";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @ResponseBody
    @PostMapping("/success")
    public String signupUser(@ModelAttribute UserSignUpDetails userSignUpDetails) {
        bloodBankService.addUser(userSignUpDetails);
        return "success";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/userLogin")
    public String userLogin(UserLoginDetails userLoginDetails, Model model) {
        UserModel user = bloodBankService.getUser(userLoginDetails.getUsername());
        if (user != null && bloodBankService.authenticateUser(userLoginDetails)) {
            model.addAttribute("user", user);
//            if (bloodBankService.authenticateUser(userLoginDetails)) {
//                if (bloodBankService.isFirstLogin(userLoginDetails.getUsername())) {
//                    // Redirect to update password page
//                    return "updatePassword";
//                }
//            } else {
                if (user.getRole().equalsIgnoreCase("ADMIN")) {
                    List<UserModel> userModelList = bloodBankService.getAllUsers();
                    model.addAttribute("userModelList", userModelList); // Add userModelList to the model
                    return "Admin_Dashboard";
                } else if (user.getRole().equalsIgnoreCase("AGENT")) {
                    return "Agent_Dashboard";
                } else if (user.getRole().equalsIgnoreCase("END USER")) {
                    return "EndUser_Dashboard";
                }
            }
        return "login";
    }
}

