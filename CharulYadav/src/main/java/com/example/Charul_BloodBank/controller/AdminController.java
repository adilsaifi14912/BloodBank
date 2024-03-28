package com.example.Charul_BloodBank.controller;

import com.example.Charul_BloodBank.dto.UserLoginDTO;
import com.example.Charul_BloodBank.model.UserModel;
import com.example.Charul_BloodBank.dto.UserSignUpDTO;
import com.example.Charul_BloodBank.service.BloodInventoryService;
import com.example.Charul_BloodBank.service.LoginService;
import com.example.Charul_BloodBank.service.SignUpService;
import com.example.Charul_BloodBank.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdminController {

    @Autowired
    private SignUpService userSignupService;
    @Autowired
    private LoginService userLoginService;
    @Autowired
    private BloodInventoryService bloodInventoryService;
    @Autowired
    private StockService stockService;

    @PostMapping({"/usersList","/sortAndFilter"})
    public String usersList(UserLoginDTO userLoginDto, Model model,
                                @RequestParam(required = false, defaultValue = "id") String sortBy,
                                @RequestParam(required = false, defaultValue = "") String filterBy,
                                @RequestParam(required = false) String startDate,
                                @RequestParam(required = false) String endDate,
                                @RequestParam(required = false) String username)
    {
        UserModel user = userLoginService.getUser(userLoginDto.getUsername());
        model.addAttribute("user", user);
        List<UserModel> userList = userSignupService.getAllUsers(sortBy, filterBy, startDate, endDate, username);
        model.addAttribute("userList", userList);
        return "usersList";
    }

    @GetMapping("/sortAndFilter")
    public String sortedList(Model model,
                             @RequestParam(required = false, defaultValue = "id") String sortBy,
                             @RequestParam(required = false, defaultValue = "") String filterBy,
                             @RequestParam(required = false) String startDate,
                             @RequestParam(required = false) String endDate,
                             @RequestParam(required = false) String username)
    {
        List<UserModel> userList = userSignupService.getAllUsers(sortBy, filterBy, startDate, endDate, username);
        model.addAttribute("userList", userList);
        return "usersList";
    }

    @PostMapping("/checkRequestsAdmin")
    public String checkRequests(@RequestParam(required = false) String startDate,
                                @RequestParam(required = false) String endDate,
                                @RequestParam(required = false) String agentUsername,
                                @RequestParam(required = false) String endUserUsername,
                                Model model) {
        List<HashMap<String, Object>> bloodBankRequests;
        if (startDate != null || endDate != null || agentUsername != null || endUserUsername != null) {
            bloodBankRequests = bloodInventoryService.getFilteredBloodBankList(startDate, endDate, agentUsername, endUserUsername);
        } else {
            bloodBankRequests = bloodInventoryService.getBloodBankList();
        }
        model.addAttribute("bloodBankRequests", bloodBankRequests);
        return "checkRequestsAdmin";
    }
    @PostMapping("/declineRequestWithRemarks")
    public String declineRequestWithRemarks(@RequestParam("requestId") Long requestId, @RequestParam("remarks") String remarks) {
        bloodInventoryService.declineRequestWithRemarks(requestId, remarks);
        return "checkRequestsAdmin";
    }
    @PostMapping("/acceptRequest")
    public String acceptRequest(@RequestParam("requestId") Long requestId,
                                @RequestParam("unitsDonated") int unitsDonated,
                                @RequestParam String type,
                                @RequestParam String bloodGroup,
                                @RequestParam String username) {
        bloodInventoryService.acceptRequest(requestId, unitsDonated);
        if("donate".equalsIgnoreCase(type))
            userSignupService.updateCoins(username,bloodGroup,unitsDonated);
        return "checkRequestsAdmin";
    }
    @PostMapping("/agentCreationForm")
    public String agentCreationForm() {
        return "agentCreationForm";
    }

    @PostMapping("/createAgent")
    public String createAgentByAdmin(@ModelAttribute UserSignUpDTO userSignUpDetails){
        userSignupService.insert(userSignUpDetails,"agent","Admin");
        return "login";
    }
    @PostMapping("/checkStock")
    public String stockAvailability(Model model) {
        model.addAttribute("unitsByBloodGroup", stockService.getUnitsByBloodGroup());
        return "stockAvailability";
    }
    @PostMapping("/requestReportAdmin")
    public String generateRequestReport(Model model) {
        List<Map<String, Object>> requestReport = bloodInventoryService.generateRequestReport();
        model.addAttribute("requestReport", requestReport);
        return "showRequestReport";
    }
    @PostMapping("/usersCoins")
    public String usersCoins(Model model)
    {
        List<Map<String, Object>> coinReport = bloodInventoryService.getUsersCoins();
        model.addAttribute("userList", coinReport);
        return "usersCoins";
    }
}