package com.blood.Shreya_BloodBank.Controller;

import com.blood.Shreya_BloodBank.Repository.UserRepository;
import com.blood.Shreya_BloodBank.Service.BloodBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class BloodBankController {
    @Autowired
    private BloodBankService bloodBankService;

    @RequestMapping("/")
    public String welcome(){
        return "welcome";
    }


    @PostMapping("/signup")
    public String addData(@RequestParam String username,
                          @RequestParam String password,
                          @RequestParam String name,
                          @RequestParam Long phone)
    {
        bloodBankService.addUser(username,password,name,phone);
        return "success";
    }


    @GetMapping("/login")
    public String showLoginForm(){
        return "login";
    }



}
