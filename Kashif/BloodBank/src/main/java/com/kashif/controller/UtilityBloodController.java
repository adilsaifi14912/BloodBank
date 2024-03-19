package com.kashif.controller;

import com.kashif.dto.BloodStockDTO;
import com.kashif.dto.RegistrationDTO;
import com.kashif.entity.UserRegistration;
import com.kashif.repository.BloodRepo;
import com.kashif.service.BloodStockService;
import com.kashif.service.UtilityService;
import com.kashif.service.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.List;

@Controller()
public class UtilityBloodController {


    @Autowired
    UtilityService utilityService;
    @Autowired
    BloodStockService bloodStockService;
    @Autowired
    private UserRegistrationService userRegistrationService;

    @GetMapping("/dashboard/donate-blood")
    public String donateBlood(HttpSession session, Model mp){
        RegistrationDTO userData = (RegistrationDTO) session.getAttribute("data");
        if(userData==null){
            mp.addAttribute("errorMsg", "You are not logged In");
            return "redirect:/login";
        };
        return "enduser/donate-blood";
    }

    @GetMapping("/dashboard/receive-blood")
    public String receiveBlood(HttpSession session, Model mp){
        RegistrationDTO userData = (RegistrationDTO) session.getAttribute("data");
        if(userData==null){
            mp.addAttribute("errorMsg", "You are not logged In");
            return "redirect:/login";
        };

        return "enduser/receive-blood";
    }
    @PostMapping(value = {"/dashboard/donate-blood", "/dashboard/receive-blood"})
    public String receiveBloodSubmit(BloodStockDTO bloodStockDTO, HttpSession session, Model mp){
        RegistrationDTO userData = (RegistrationDTO) session.getAttribute("data");
        if(userData==null){
            session.setAttribute("errorMsg", "You are not logged In");
            return "redirect:/login";
        };
        if(bloodStockDTO.getQuantity()==null || bloodStockDTO.getQuantity()<=0){
            mp.addAttribute("errorMsg", "Blood Quantity must be positive.");
            return "enduser/receive-blood";
        }
        bloodStockDTO.setBloodGroup(userData.getBloodGroup());
        bloodStockDTO.setCreatedBy(userData.getUsername());

        bloodStockDTO.setUser(userRegistrationService.convertRegistrationDTOtoEntity(userData));
        bloodStockService.addBloodInfo(bloodStockDTO);
        mp.addAttribute("success", "Request Successfully: check status");
        if(bloodStockDTO.getType().equalsIgnoreCase("donor"))
            return "enduser/donate-blood";
        return "enduser/receive-blood";
    }

    @GetMapping("/dashboard/blood-request")
    public String bloodRequest(HttpSession session, Model mp){
        RegistrationDTO userData = (RegistrationDTO) session.getAttribute("data");
        if(userData==null){
            mp.addAttribute("errorMsg", "You are not logged In");
            return "redirect:/login";
        };
        mp.addAttribute("bloodStocks", bloodStockService.getBloodBankList());
        return "admin/blood-request";
    }

    @PostMapping("/dashboard/blood-request")
    public String bloodRequestUpdate(@RequestParam String id, @RequestParam String status, Model mp, HttpSession session){
        RegistrationDTO userData = (RegistrationDTO) session.getAttribute("data");
        if(userData==null){
            mp.addAttribute("errorMsg", "You are not logged In");
            return "redirect:/login";
        };
        Long user_id = Long.parseLong(id);
        bloodStockService.updateStatusById(status, user_id);
        mp.addAttribute("bloodStocks", bloodStockService.getBloodBankList());
        return "admin/blood-request";

    }
    @GetMapping("/dashboard/enduser-lists")
    public String endUserLists(Model mp, HttpSession session){
        System.out.println("Mai pahle");
        System.out.println(utilityService.getAllActiveUsers().size());
        System.out.println("Mai baad me");
        RegistrationDTO userData = (RegistrationDTO) session.getAttribute("data");
        if(userData==null){
            return "redirect:/login";
        };
        if (userData.isNewUser()) {
            return "redirect:/update-password";
        }
        if (userData.getRole().equalsIgnoreCase("admin")) {
            mp.addAttribute("allUsers", userRegistrationService.findAllEndUsers());
            return "admin/enduser-lists";
        }
        if (userData.getRole().equalsIgnoreCase("agent")) {
            mp.addAttribute("allUsers", utilityService.filterByCurrentAgent(userRegistrationService.getAllUsers(), userData));
            return "agent/enduser-lists";
        }

        return "redirect:/dashboard";
    }

    @PostMapping("/dashboard/enduser-lists")
    public String endUserUpdatedLists(HttpSession session, Model mp, @RequestParam String sortOption, @RequestParam String filterOption, @RequestParam String username, @RequestParam String startDate, @RequestParam String endDate) throws ParseException {
        RegistrationDTO userData = (RegistrationDTO) session.getAttribute("data");
        if(userData==null){
            return "redirect:/login";
        };
        if (userData.isNewUser()) {
            return "redirect:/update-password";
        }
        List<RegistrationDTO> allUsersList = userRegistrationService.findAllEndUsers();
        if(userData.getRole().equalsIgnoreCase("agent")){
           allUsersList = utilityService.filterByCurrentAgent(allUsersList, userData);
        }
        if(sortOption!= null){
            allUsersList = utilityService.sortUsers(sortOption, allUsersList);
            mp.addAttribute("allUsers", allUsersList);
            mp.addAttribute("operation", "Sorting Applied");
        }

        if(filterOption.equalsIgnoreCase("notLoggedIn")){
           mp.addAttribute("allUsers", utilityService.notLoggedInUser(allUsersList));
           mp.addAttribute("operation", "Filtering Applied");
        }

        if(filterOption.equalsIgnoreCase("byAgent") && !userData.getRole().equalsIgnoreCase("agent")){
            mp.addAttribute("allUsers", utilityService.filterByAgent(allUsersList));
            mp.addAttribute("operation", "Filtering Applied");
        }
        System.out.println("Endate: " +endDate);
        if(startDate != null && !startDate.isEmpty() && endDate != null && !endDate.isEmpty()){
            mp.addAttribute("allUsers", utilityService.filterByDate(startDate, endDate, allUsersList));
            mp.addAttribute("operation", "Filtering Applied");
        }

        if(username != null && !username.isEmpty()){
            mp.addAttribute("allUsers", utilityService.filterByUsername(username,allUsersList));
            mp.addAttribute("operation", "Filtering Applied");
        }

        if(sortOption != null && (filterOption.equalsIgnoreCase("notLoggedIn"))){
            mp.addAttribute("operation", "Sorting or Filtering Applied");
        }
        return userData.getRole().equalsIgnoreCase("admin") ? "admin/enduser-lists": "agent/enduser-lists";
    }

    @GetMapping("/dashboard/status")
    public String statusEndUser(){
        return "enduser/status";
    }

}
