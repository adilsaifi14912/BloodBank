package com.insightgeeks.bloodbank.controller;

import com.insightgeeks.bloodbank.dto.BloodRequestDTO;
import com.insightgeeks.bloodbank.dto.SignupDTO;
import com.insightgeeks.bloodbank.service.AdminService;
import com.insightgeeks.bloodbank.service.AgentService;
import com.insightgeeks.bloodbank.service.BloodStockService;
import com.insightgeeks.bloodbank.service.UserService;
import com.insightgeeks.bloodbank.util.BloodRequests;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class UsersController {

    @Autowired
    AdminService adminService;

    @Autowired
    BloodRequests bloodRequests;


    @Autowired
    AgentService agentService;

    @Autowired
    UserService userService;

    @Autowired
    BloodStockService bloodStockService;

    public  String agentName;

    // Retrieve blood requests
//    @GetMapping("/processBloodRequests")
//    public String getBloodRequests(Model model, HttpServletRequest httpServletRequest) {
//        HttpSession session = httpServletRequest.getSession();
//        SignupDTO user = (SignupDTO)session.getAttribute("user");
//          model.addAttribute("bloodRequests", bloodRequests.getBloodRequestLists(user));
//        return "bloodRequests";
//    }

    // Sort users
    @GetMapping("/sort/{by}")
    public String sortSignupUsers(@PathVariable String by, Model model) {
        model.addAttribute("sortedUsers", adminService.sortUsers(by));
        return "signupUsersDetails";
    }

    // Filter users
    @GetMapping("/filter/{by}")
    public String filterSignupUsers(@PathVariable String by, Model model, @RequestParam String filterValue) {
        try {
            List<SignupDTO> filteredUser = adminService.filterSignedUpCreatedUsers(by, filterValue);
            model.addAttribute("sortedUsers", filteredUser);
            return "signupUsersDetails";
        } catch (Exception e) {
            return "signupUserDetails";
        }
    }

    // Retrieve agent-created users
    @RequestMapping("/agentCreatedUsers")
    public String getAgentCreatedUsers(HttpServletRequest httpServletRequest) {
        HttpSession httpSession = httpServletRequest.getSession();
        SignupDTO user = (SignupDTO) httpSession.getAttribute("user");
        httpSession.setAttribute("agentCreatedUsers", agentService.getAgentCreatedUsers(user.getUsername()));
        return "agentCreatedUsers";
    }

    // Sort agent-created users
    @RequestMapping("/agentSort/{by}")
    public String sortAgentCreatedUsers(@PathVariable String by,
                                        HttpServletRequest httpServletRequest, Model model) {
        HttpSession httpSession = httpServletRequest.getSession();
        SignupDTO user = (SignupDTO) httpSession.getAttribute("user");
        try{
            if(user.getRole().equals("agent")){
                agentName = user.getUsername();
                model.addAttribute("sortedUsers", agentService.sortUsers(by, user.getUsername()));
            }
            else{
        model.addAttribute("sortedUsers", agentService.sortUsers(by, agentName));
            }

        }catch (Exception e)
        {
//            Handle Exception
        }
        return "agentCreatedUsers";
    }

    // Filter agent-created users
    @RequestMapping("/agentFilter/{by}")
    public String filterAgentCreatedUsers(@PathVariable String by,
                                          HttpServletRequest httpServletRequest,
                                          Model model, @RequestParam String filterValue) {
        HttpSession httpSession = httpServletRequest.getSession();
        SignupDTO user = (SignupDTO) httpSession.getAttribute("user");
        try {
            if(user.getRole().equals("agent"))
            {
                agentName = user.getUsername();
            List<SignupDTO> filteredAgentUsers = agentService.filterAgentCreatedUsers(by, filterValue, user.getUsername());
            model.addAttribute("sortedUsers", filteredAgentUsers);
            }else {
                List<SignupDTO> filteredAgentUsers = agentService.filterAgentCreatedUsers(by, filterValue, agentName);
                model.addAttribute("sortedUsers", filteredAgentUsers);
            }
        } catch (Exception e) {
            // Handle exception
        }
        return "agentCreatedUsers";
    }





    @PostMapping("/bloodRequirement")
    public String bloodRequest(@ModelAttribute @Validated BloodRequestDTO bloodRequestDTO,
                               BindingResult bindingResult, Model model, HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        SignupDTO userInformation = (SignupDTO) session.getAttribute("user");

        if (bindingResult.hasErrors()) {
            model.addAttribute("formatError", bindingResult.getFieldError().getDefaultMessage());
            return "bloodRequest";
        }
        try {
            userService.handleBloodRequirementRequest(bloodRequestDTO, userInformation);
            return "endUserProfilePage";
        } catch (Exception e) {
            model.addAttribute("formatError", e.getMessage());
            return "bloodRequest";
        }
    }




    @GetMapping("/accept")
    public String approveBloodRequest(@RequestParam int id, Model model, HttpServletRequest httpServletRequest) {


        HttpSession session = httpServletRequest.getSession();
        SignupDTO user = (SignupDTO)session.getAttribute("user");

        model.addAttribute("bloodRequests", bloodRequests.getBloodRequestLists((SignupDTO)session.
                getAttribute("user"),"","",""));
       if(adminService.acceptBloodRequest(id)) {
           bloodStockService.updateBloodStock(id);
           userService.updateNextRequestEligibleDate(id);
           userService.updateUsersCoins(id);
           agentService.updateCoins(id);
           adminService.updateCoins(id);
       }
        return "bloodRequests";
    }




    @PostMapping("/reject")
    public String rejectBloodRequest(@RequestParam int id, @RequestParam String remark,Model model,
                                     HttpServletRequest httpServletRequest)
    {
        adminService.rejectBloodRequest(id,remark);
        HttpSession session = httpServletRequest.getSession();
        model.addAttribute("bloodRequests", bloodRequests.getBloodRequestLists((SignupDTO)session.
                getAttribute("user"),"","",""));
        return "bloodRequests";
    }



    @GetMapping("/myRequests")
    public String showUsersRequest(HttpServletRequest httpServletRequest, Model model)
    {
        HttpSession session = httpServletRequest.getSession();
        SignupDTO user = (SignupDTO)session.getAttribute("user");
        model.addAttribute("bloodRequests", bloodRequests.getBloodRequestLists(user,"","",""));

        if (user.getRole().equals("admin")) {
            return "bloodRequests";
        } else if (user.getRole().equals("user")) {
            return "endUserBloodRequests";
        } else {
            return "agentBloodRequests";
        }

    }



    @GetMapping("bloodRequestsDateFilter")
    public String getRequestsBetweenDates(@RequestParam String from, @RequestParam String to,
                                          HttpServletRequest httpServletRequest, Model model)
    {
        HttpSession session = httpServletRequest.getSession();
        SignupDTO user = (SignupDTO)session.getAttribute("user");
       model.addAttribute("bloodRequests",bloodRequests.getBloodRequestLists(user, from, to, ""));

        if (user.getRole().equals("admin")) {
            return "bloodRequests";
        } else if (user.getRole().equals("user")) {
            return "endUserBloodRequests";
        } else {
            return "agentBloodRequests";
        }

    }

    @GetMapping("bloodRequestsStatusFilter")
    public String getRequestsByStatus(@RequestParam String status,
                                          HttpServletRequest httpServletRequest, Model model)
    {
        HttpSession session = httpServletRequest.getSession();
        SignupDTO user = (SignupDTO)session.getAttribute("user");
//        model.addAttribute("bloodRequests",bloodRequests.getBloodRequestLists(user, from, to));
            model.addAttribute("bloodRequests",bloodRequests.getBloodRequestLists(user,"","", status));
        if (user.getRole().equals("admin")) {
            return "bloodRequests";
        } else if (user.getRole().equals("user")) {
            return "endUserBloodRequests";
        } else {
            return "agentBloodRequests";
        }

    }

@GetMapping("/requestsReport")
public String getRequestsReport(Model model, HttpServletRequest httpServletRequest) {

        HttpSession session = httpServletRequest.getSession();
        SignupDTO user = (SignupDTO) session.getAttribute("user");

        Map<String, Map<String, Map<String, Integer>>> bloodReport = bloodStockService.calculateStatistics(user);
        model.addAttribute("bloodReport", bloodReport);
        return "requestsReport";
    }

}
















