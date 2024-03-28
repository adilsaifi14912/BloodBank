package com.example.Charul_BloodBank.controller;

import com.example.Charul_BloodBank.dto.BloodInventoryDTO;
import com.example.Charul_BloodBank.dto.UserLoginDTO;
import com.example.Charul_BloodBank.dto.UserSignUpDTO;
import com.example.Charul_BloodBank.model.UserModel;
import com.example.Charul_BloodBank.service.BloodInventoryService;
import com.example.Charul_BloodBank.service.LoginService;
import com.example.Charul_BloodBank.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    private SignUpService userSignupService;
    @Autowired
    private LoginService userLoginService;

    @Autowired
    private BloodInventoryService bloodInventoryService;

    @RequestMapping("/")
    public String login() {
        return "login";
    }

    @RequestMapping("/signup")
    public String signup() {
        return "signup";
    }

    @PostMapping("/userLogin")
    public String userLogin(UserLoginDTO userLoginDto, Model model, HttpSession session) {
        UserModel user = userLoginService.getUser(userLoginDto.getUsername());
        if(user.isLocked()){
            return "accountLocked";
        }
        if (user!=null)
        {
            boolean auth = userLoginService.authenticate(userLoginDto);
            if(auth) {
                session.setAttribute("user", user);
                if (user.isFirstLogin()) {
                    session.setAttribute("user", user);
                    if(!user.getCreatedBy().equalsIgnoreCase("AUTO")||user.getRole().equalsIgnoreCase("agent"))
                        return "resetPass";
                }
                if (user.isLocked()) {
                    return "accountLocked";
                }
                String userRole = user.getRole();
                model.addAttribute("user", user);
                if ("admin".equalsIgnoreCase(userRole)) {
                    return "adminDashboard";
                } else if ("agent".equalsIgnoreCase(userRole)) {
                    return "agentDashboard";
                } else if ("enduser".equalsIgnoreCase(userRole)) {
                    return "endUserDashboard";
                } else {
                    return "login";
                }
            }
        }
        return "login";
    }
    @PostMapping("/updatePassword")
    public String passwordUpdate(@RequestParam String username,
                                 @RequestParam String newPassword)
    {
        userLoginService.updateUserPassword(newPassword, username);
        return "login";
    }
    @ResponseBody
    @PostMapping("/register")
    public String register(@ModelAttribute @Validated UserSignUpDTO userDto) {
                userSignupService.insert(userDto,"enduser","AUTO");
        return "Registered";
    }

    //End User Creation by Agent
    @GetMapping("/endUserCreationFormByAgent")
    public String endUserCreationFormByAgent() {
        return "endUserCreation";
    }
    @PostMapping("/endUserCreationForm")
    public String endUserCreationForm(@ModelAttribute UserSignUpDTO userSignUpDTO, HttpSession session){
        UserModel user = (UserModel) session.getAttribute("user");
        if (user != null) {
            userSignupService.insert(userSignUpDTO, "enduser", user.getUsername());
        } else {
            return "endUserCreationFormByAgent";
        }
        return "login";
    }
    @GetMapping("/endUserBloodBankInventory")
    public String bloodSubmit() {
        return "endUserBloodBankInventory";
    }
    @ResponseBody
    @PostMapping("/bloodSubmit")
    public String bloodSubmitForm(@ModelAttribute BloodInventoryDTO bloodInventoryDTO, HttpSession session) {
        UserModel user = (UserModel) session.getAttribute("user");
        if (user != null) {
            // Check if the user has made a donation or receive request before
            if (bloodInventoryDTO.getType().equalsIgnoreCase("donate")) {
                Optional<LocalDateTime> lastDonationDate = bloodInventoryService.getLastDonationDate(user.getUsername());
                System.out.println(lastDonationDate);
                if (lastDonationDate.isPresent()) {
                    LocalDateTime now = LocalDateTime.now();
                    // Check if the last donation was made more than 3 months ago
                    if (lastDonationDate.get().plusMonths(3).isBefore(now)) {
                        bloodInventoryDTO.setUserid(user);
                        bloodInventoryService.addUser(bloodInventoryDTO, user.getCreatedBy());
                        return "Request Successfully Sent";
                    } else {
                        return "You can donate blood only after 3 months of your last donation.";
                    }
                } else {
                    // User has not made a donation before, proceed with adding the request
                    bloodInventoryDTO.setUserid(user);
                    bloodInventoryService.addUser(bloodInventoryDTO, user.getCreatedBy());
                    return "Request Successfully Sent";
                }
            } else {
                // Current request is for receiving blood, proceed with adding the request
                bloodInventoryDTO.setUserid(user);
                bloodInventoryService.addUser(bloodInventoryDTO, user.getCreatedBy());
                return "Request Successfully Sent";
            }
        } else {
            return "User not logged in.";
        }
    }
    @RequestMapping("/checkRequestsAG")
    public String checkRequestsAG(@RequestParam(required = false) String startDate,
                                  @RequestParam(required = false) String endDate,
                                  @RequestParam(required = false) String endUserUsername,
                                  Model model, HttpSession session)
    {
        List<HashMap<String, Object>> bloodBankRequests;
        if (startDate != null || endDate != null || endUserUsername != null){
            bloodBankRequests= bloodInventoryService.filteredAgentRequests(startDate, endDate, endUserUsername, ((UserModel)session.getAttribute("user")).getUsername());
        } else{
            bloodBankRequests= bloodInventoryService.agentRequests(((UserModel)session.getAttribute("user")).getUsername());
        }
        model.addAttribute("bloodBankRequests", bloodBankRequests);
        return "checkRequestsAG";
    }
    @PostMapping("/checkRequestsEU")
    public String checkRequestsEU(@RequestParam(required = false) String startDate,
                                  @RequestParam(required = false) String endDate,
                                  Model model, HttpSession session) {
        List<HashMap<String, Object>> bloodBankRequests;
        if (startDate != null || endDate != null){
            bloodBankRequests= bloodInventoryService.filteredEURequests(startDate, endDate, ((UserModel)session.getAttribute("user")).getUsername());
        } else{
            bloodBankRequests= bloodInventoryService.euRequests(((UserModel)session.getAttribute("user")).getUsername());
        }
        model.addAttribute("bloodBankRequests", bloodBankRequests);
        return "checkRequestsEU";
    }
    @GetMapping("/requestReportAG")
    public String generateRequestReport(Model model, HttpSession session) {
        List<Map<String, Object>> requestReport = bloodInventoryService.agentRequestReport(((UserModel)session.getAttribute("user")).getUsername());
        model.addAttribute("requestReport", requestReport);
        return "showRequestReport";
    }
    @GetMapping("/bloodGroupAgentRate")
    public String bloodGroupAgentRate(Model model, HttpSession session) {
        List<Map<String, Object>> requestReport = bloodInventoryService.agentRate(((UserModel)session.getAttribute("user")).getUsername());
        model.addAttribute("requestReport", requestReport);
        return "bloodGroupAgentRate";
    }
    @RequestMapping("/agentCoinReport")
    public String agentCoinReport(Model model, HttpSession session)
    {
        List<Map<String, Object>> coinReport = bloodInventoryService.getAgentCoinReport(((UserModel)session.getAttribute("user")).getUsername());
        model.addAttribute("userList", coinReport);
        return "agentCoinReport";
    }
}