package com.BloodBank.Controller;

import com.BloodBank.Model.UserModel;
import com.BloodBank.Service.BloodRequestService;
import com.BloodBank.Service.BloodStockService;
import com.BloodBank.Service.UserService;
import com.BloodBank.dto.BloodRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Controller
public class RequestController {

    @Autowired
    private UserService userService;

    @Autowired
    private HttpSession session;

    @Autowired
    private BloodRequestService bloodRequestService;

    @Autowired
    private BloodStockService bloodStockService;

    @RequestMapping("/createRequest")
    public String createRequest()
    {
        return "requestForm";
    }

    @ResponseBody
    @PostMapping("/addRequest")
    public String addRequest(BloodRequestDTO bloodRequestDTO)
    {
        UserModel user = (UserModel) session.getAttribute("userLoggedIn");
        if (user != null) {
            // Check if the user has made a donation or receive request before
            if (bloodRequestDTO.getType().equalsIgnoreCase("donate")) {
                Optional<LocalDateTime> lastDonationDate = bloodRequestService.getLastDonationDate(user.getUsername());
                System.out.println(lastDonationDate);
                if (lastDonationDate.isPresent()) {
                    LocalDateTime now = LocalDateTime.now();
                    // Check if the last donation was made more than 3 months ago
                    if (lastDonationDate.get().plusMonths(3).isBefore(now)) {
                        bloodRequestDTO.setUserId(user);
                        bloodRequestService.addRequest(bloodRequestDTO);
                        return "Request Successfully Sent";
                    } else {
                        return "You can donate blood only after 3 months of your last donation.";
                    }
                } else {
                    // User has not made a donation before, proceed with adding the request
                    bloodRequestDTO.setUserId(user);
                    bloodRequestService.addRequest(bloodRequestDTO);
                    return "Request Successfully Sent";
                }
            } else {
                // Current request is for receiving blood, proceed with adding the request
                bloodRequestDTO.setUserId(user);
                bloodRequestService.addRequest(bloodRequestDTO);
                return "Request Successfully Sent";
            }
        } else {
            return "User not logged in.";
        }
    }


    @PostMapping("/showRequest")
    public String showRequest(@RequestParam(required = false) String startDate,
                                @RequestParam(required = false) String endDate,
                                @RequestParam(required = false) String agentUsername,
                                @RequestParam(required = false) String endUsername,
                                Model model) {
        List<HashMap<String, Object>> bloodBankRequests;
        if (startDate != null || endDate != null || agentUsername != null || endUsername != null) {
            bloodBankRequests = bloodRequestService.getFilteredAllRequests(startDate, endDate, agentUsername, endUsername);
        } else {
            bloodBankRequests = bloodRequestService.getAllRequests();
        }
        model.addAttribute("bloodBankRequests", bloodBankRequests);
        return "requestsTable";
    }

    @PostMapping("/processRequest")
    public String processRequest(@RequestParam Long requestId,
                                 @RequestParam String action,
                                 @RequestParam String bloodGroup,
                                 @RequestParam int quantity,
                                 @RequestParam String type,
                                 @RequestParam String username) {
        if ("approve".equals(action)) {
            bloodRequestService.updateStatus(requestId, "Approved");
            bloodRequestService.updateTime(requestId);

            if("donate".equals(type))
            {
                userService.updateCoins(username,bloodGroup,quantity);
                bloodStockService.updateStock(bloodGroup,quantity);
            }
        } else if ("reject".equals(action)) {
            bloodRequestService.updateStatus(requestId, "Rejected");
        }
        return "requestsTable";
    }

    @RequestMapping("/endUserRequestStatus")
    public String endUserRequestStatus(Model model,
                                       @RequestParam(required = false) String startDate,
                                       @RequestParam(required = false) String endDate)
    {
        List<HashMap<String, Object>> bloodBankRequests;
        if (startDate != null || endDate != null){
            bloodBankRequests= bloodRequestService.filteredEURequests(startDate, endDate, ((UserModel)session.getAttribute("userLoggedIn")).getUsername());
        } else{
            bloodBankRequests= bloodRequestService.endUserRequestStatus(((UserModel)session.getAttribute("userLoggedIn")).getUsername());
        }
        model.addAttribute("bloodBankRequests", bloodBankRequests);
        return "endUserRequestStatus";
    }

    @PostMapping("/agentRequestStatus")
    public String agentRequestStatus(@RequestParam(required = false) String startDate,
                                  @RequestParam(required = false) String endDate,
                                  @RequestParam(required = false) String endUsername,
                                  Model model)
    {
        List<HashMap<String, Object>> bloodBankRequests;
        if (startDate != null || endDate != null || endUsername != null){
            bloodBankRequests= bloodRequestService.filteredAgentRequests(startDate, endDate, endUsername, ((UserModel)session.getAttribute("userLoggedIn")).getUsername());
        } else{
            bloodBankRequests= bloodRequestService.agentRequestStatus(((UserModel)session.getAttribute("userLoggedIn")).getUsername());
        }
        model.addAttribute("bloodBankRequests", bloodBankRequests);
        return "agentRequestStatus";
    }
}
