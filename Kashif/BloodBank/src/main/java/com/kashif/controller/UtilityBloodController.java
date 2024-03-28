package com.kashif.controller;

import com.kashif.dto.BloodRequestDTO;
import com.kashif.dto.BloodStockDTO;
import com.kashif.dto.RegistrationDTO;
import com.kashif.service.BloodRequestService;
import com.kashif.service.BloodStockService;
import com.kashif.service.UtilityService;
import com.kashif.service.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

@Controller()
public class UtilityBloodController {


    @Autowired
    private UtilityService utilityService;
    @Autowired
    private BloodRequestService bloodRequestService;
    @Autowired
    private UserRegistrationService userRegistrationService;
    @Autowired
    private BloodStockService bloodStockService;

    @GetMapping("/dashboard/donate-blood")
    public String donateBlood(HttpSession session, Model mp) {
        RegistrationDTO userData = (RegistrationDTO) session.getAttribute("data");
        if (userData == null) {
            mp.addAttribute("errorMsg", "You are not logged In");
            return "redirect:/login";
        }
        ;
        return "enduser/donate-blood";
    }

    @GetMapping("/dashboard/receive-blood")
    public String receiveBlood(HttpSession session, Model mp) {
        RegistrationDTO userData = (RegistrationDTO) session.getAttribute("data");
        if (userData == null) {
            return "redirect:/login";
        }
        ;
        if (userData.isNewUser()) {
            return "redirect:/update-password";
        }

        return "enduser/receive-blood";
    }

    @PostMapping(value = {"/dashboard/donate-blood", "/dashboard/receive-blood"})
    public String receiveBloodSubmit(BloodRequestDTO bloodRequestDTO, HttpSession session, Model mp) {
        RegistrationDTO userData = (RegistrationDTO) session.getAttribute("data");
        if (userData == null) {
            session.setAttribute("errorMsg", "You are not logged In");
            return "redirect:/login";
        }

        if (bloodRequestDTO.getQuantity() == null || bloodRequestDTO.getQuantity() <= 0) {
            mp.addAttribute("errorMsg", "Quantity must be positive.");
            if (bloodRequestDTO.getType().equalsIgnoreCase("donor"))
                return "enduser/donate-blood";
            return "enduser/receive-blood";
        }
        if (bloodRequestDTO.getType().equalsIgnoreCase("donor")) {
            if(Period.between(LocalDate.parse(userData.getDob()), LocalDate.now()).getYears() < 18){
                mp.addAttribute("errorMsg", "You'r underage! Request when you are 18 years Old");
                return "enduser/donate-blood";
            }
            String msg = utilityService.eligibleToDonate(userData.getUsername());
            if (!msg.equalsIgnoreCase("ok")) {
                mp.addAttribute("errorMsg", msg);
                return "enduser/donate-blood";
            }
        }
        bloodRequestDTO.setBloodGroup(userData.getBloodGroup());
        bloodRequestDTO.setCreatedBy(userData.getUsername());

        bloodRequestDTO.setUser(userData);
        bloodRequestService.addBloodInfo(bloodRequestDTO);
        mp.addAttribute("success", "Request Successfully: check status");
        if (bloodRequestDTO.getType().equalsIgnoreCase("donor"))
            return "enduser/donate-blood";
        return "enduser/receive-blood";
    }

    @GetMapping("/dashboard/blood-request")
    public String bloodRequest(HttpSession session, Model mp) {
        RegistrationDTO userData = (RegistrationDTO) session.getAttribute("data");
        if (userData == null) {
            mp.addAttribute("errorMsg", "You are not logged In");
            return "redirect:/login";
        }
        if (!userData.getRole().equalsIgnoreCase("admin")) return "redirect:/dashboard";
        mp.addAttribute("bloodStocks", bloodRequestService.getBloodBankList());
        return "admin/blood-request";
    }

    @PostMapping("/dashboard/blood-request")
    public String bloodRequestUpdate(@RequestParam Map<String, String> requestParams, Model mp, HttpSession session) throws ParseException {
        RegistrationDTO userData = (RegistrationDTO) session.getAttribute("data");
        if (userData == null) {
            mp.addAttribute("errorMsg", "You are not logged In");
            return "login";
        }

        if (!userData.getRole().equalsIgnoreCase("admin")) return "redirect:/dashboard";
        long id = requestParams.get("id") != null ? Long.parseLong(requestParams.get("id")) : 0;
        String bloodStatus = requestParams.get("bloodStatus");
        String remark = requestParams.get("remark") == null || requestParams.get("remark").isBlank() ? "-" : requestParams.get("remark");
        long quantity = requestParams.get("quantity") == null ? 0 : Long.parseLong(requestParams.get("quantity"));
        String bloodGroup = requestParams.get("bloodGroup");
        String type = requestParams.get("type");
        String filter = requestParams.get("filterOption");
        if (filter == null && bloodStatus == null) {
            mp.addAttribute("errorMsg", "Field Can't be Empty");
            return "admin/blood-request";
        }
        // Here Request List
        List<HashMap<String, Object>> requestList = bloodRequestService.getBloodBankList();

        if (bloodStatus != null && bloodStatus.equalsIgnoreCase("accepted")) {
            String msg = bloodStockService.updateBloodStock(quantity, new Date(), bloodGroup, type, id);
            if (!msg.equalsIgnoreCase("ok")) {
                mp.addAttribute("errorMsg", msg);
                mp.addAttribute("bloodStocks", requestList);
                return "admin/blood-request";
            }

        }
        bloodRequestService.updateStatusAndRemarkById(bloodStatus, remark, userData.getUsername(), id);
        requestList = bloodRequestService.getBloodBankList();
        mp.addAttribute("bloodStocks", requestList);
        if (bloodStatus!=null && bloodStatus.equalsIgnoreCase("accepted"))
            mp.addAttribute("success", "Request Accepted Successfully");
        else mp.addAttribute("success", "Request Rejected Successfully");


        // Date filter applied here
        if (filter != null && filter.equalsIgnoreCase("createdBetween")) {
            String startDate = requestParams.get("startDate");
            String endDate = requestParams.get("endDate");
            mp.addAttribute("bloodStocks", utilityService.filterBloodRequestByDate(startDate, endDate, requestList));
            mp.addAttribute("success", "Filter Applied");
        }
        // Status filter (accept/pending/rejected)
        if (filter != null) {
            String input = filter.equalsIgnoreCase("byStatus")
                    ? requestParams.get("status")
                    : filter.equalsIgnoreCase("byAgent")
                    ? requestParams.get("agent")
                    : filter.equalsIgnoreCase("byUsername")
                    ? requestParams.get("username")
                    : "none";
            mp.addAttribute("success", "Filter Applied");
            requestList = utilityService.filterByQueryBloodRequest(requestList, filter, input);
        }
        mp.addAttribute("bloodStocks", requestList);
        return "admin/blood-request";
    }

    @GetMapping({"/dashboard/alluser-lists", "/dashboard/enduser-lists"})
    public String endUserLists(Model mp, HttpSession session) {
        System.out.println(utilityService.getAllActiveUsers().size());
        RegistrationDTO userData = (RegistrationDTO) session.getAttribute("data");
        if (userData == null) {
            return "redirect:/login";
        }
        ;
        if (userData.isNewUser()) {
            return "redirect:/update-password";
        }
        if (userData.getRole().equalsIgnoreCase("admin")) {
            mp.addAttribute("allUsers", userRegistrationService.getAllUsers());
            return "admin/alluser-lists";
        }
        if (userData.getRole().equalsIgnoreCase("agent")) {
            mp.addAttribute("allUsers", utilityService.filterByCurrentAgent(userRegistrationService.getAllUsers(), userData));
            return "agent/enduser-lists";
        }

        return "redirect:/dashboard";
    }

    @PostMapping({"/dashboard/alluser-lists", "/dashboard/enduser-lists"})
    public String endUserUpdatedLists(HttpSession session, Model mp, @RequestParam Map<String, String> requestParams) throws ParseException {
        RegistrationDTO userData = (RegistrationDTO) session.getAttribute("data");
        if (userData == null) {
            return "redirect:/login";
        }
        ;
        if (userData.isNewUser()) {
            return "redirect:/update-password";
        }

        String sortOption = requestParams.get("sortOption");
        String filterOption = requestParams.get("filterOption");
        String username = requestParams.get("username");
        String startDate = requestParams.get("startDate");
        String endDate = requestParams.get("endDate");
        String agent = requestParams.get("agent");


        List<RegistrationDTO> allUsersList = userRegistrationService.getAllUsers();

        if (userData.getRole().equalsIgnoreCase("agent")) {
            allUsersList = utilityService.filterByCurrentAgent(allUsersList, userData);
        }
        if (sortOption != null) {
            allUsersList = utilityService.sortUsers(sortOption, allUsersList);
            mp.addAttribute("allUsers", allUsersList);
            mp.addAttribute("operation", "Sorting Applied");
        }

        if (filterOption.equalsIgnoreCase("activeUsers")) {
            mp.addAttribute("allUsers", utilityService.getAllActiveUsers());
            mp.addAttribute("operation", "Filtering Applied");
        }

        if (filterOption.equalsIgnoreCase("notLoggedInUser")) {
            mp.addAttribute("allUsers", utilityService.filterByNonLoggedInUser(allUsersList));
            mp.addAttribute("operation", "Filtering Applied");
        }


        if (filterOption.equalsIgnoreCase("byAgent") && !userData.getRole().equalsIgnoreCase("agent")) {
            mp.addAttribute("allUsers", utilityService.filterByAgent(allUsersList, agent));
            mp.addAttribute("operation", "Filtering Applied");
        }

        if (startDate != null && !startDate.isEmpty() && endDate != null && !endDate.isEmpty()) {
            mp.addAttribute("allUsers", utilityService.filterByDate(startDate, endDate, allUsersList));
            mp.addAttribute("operation", "Filtering Applied");
        }

        if (username != null && !username.isBlank()) {
            mp.addAttribute("allUsers", utilityService.filterByUsername(username, allUsersList));
            mp.addAttribute("operation", "Filtering Applied");
        }

        return userData.getRole().equalsIgnoreCase("admin") ? "admin/alluser-lists" : "agent/enduser-lists";
    }

    @RequestMapping("/dashboard/status")
    public String statusEndUser(HttpSession session, Model mp, @RequestParam Map<String, String> requestParams) throws ParseException {
        RegistrationDTO userData = (RegistrationDTO) session.getAttribute("data");
        if (userData == null) {
            return "redirect:/login";
        }

        if (userData.isNewUser()) {
            return "redirect:/update-password";
        }
        String filter = requestParams.get("filterOption");
        List<HashMap<String, Object>> list = bloodRequestService.requestBloodStatus(userData.getUsername(), userData.getRole());


        if (filter != null && filter.equalsIgnoreCase("createdBetween")) {
            String startDate = requestParams.get("startDate");
            String endDate = requestParams.get("endDate");
            list = utilityService.filterBloodRequestByDate(startDate, endDate, list);
            mp.addAttribute("success", "Filter Applied");
        }
        // Status filter (accept/pending/rejected)
        if (filter != null) {
            String input = filter.equalsIgnoreCase("byStatus")
                    ? requestParams.get("status")
                    : filter.equalsIgnoreCase("byAgent")
                    ? requestParams.get("agent")
                    : filter.equalsIgnoreCase("byUsername")
                    ? requestParams.get("username")
                    : "none";
            mp.addAttribute("success", "Filter Applied");
            list = utilityService.filterByQueryBloodRequest(list, filter, input);
        }
        mp.addAttribute("bloodRequests", list);
        return userData.getRole().equalsIgnoreCase("agent") ? "agent/status" : "enduser/status";
    }

    @GetMapping("/dashboard/blood-report")
    public String bloodReport(HttpSession session, Model mp) {
        RegistrationDTO userData = (RegistrationDTO) session.getAttribute("data");
        if (userData == null) {
            return "redirect:/login";
        }
        if (userData.isNewUser()) {
            return "redirect:/update-password";
        }
        if (userData.getRole().equalsIgnoreCase("EndUser")) return "enduser/dashboard";
        mp.addAttribute("reportAcceptedBG", bloodRequestService.listReport("accepted", userData.getUsername(), userData.getRole()));
        mp.addAttribute("reportRejectedBG", bloodRequestService.listReport("rejected", userData.getUsername(), userData.getRole()));
        return userData.getRole().equalsIgnoreCase("agent") ? "agent/blood-report"
                : userData.getRole().equalsIgnoreCase("admin") ? "admin/blood-report"
                : "redirect:/dashboard";
    }

    @GetMapping("/dashboard/available-stock")
    public String availableStock(HttpSession session, Model mp) {
        RegistrationDTO userData = (RegistrationDTO) session.getAttribute("data");
        if (userData == null) {
            return "redirect:/login";
        }
        if (userData.isNewUser()) {
            return "redirect:/update-password";
        }
        List<BloodStockDTO> list = bloodStockService.allStockList();
        long totalStock = list.stream().mapToLong(BloodStockDTO::getUnit).sum();
        mp.addAttribute("stocks", list);

        mp.addAttribute("totalStock", totalStock);
        return userData.getRole().equalsIgnoreCase("admin") ? "admin/available-stock"
                : "redirect:/dashboard";
    }

    @GetMapping("/dashboard/coins-value")
    public String coinValue(HttpSession session, Model mp) {
        RegistrationDTO userData = (RegistrationDTO) session.getAttribute("data");
        if (userData == null) {
            return "redirect:/login";
        }
        if (userData.isNewUser()) {
            return "redirect:/update-password";
        }

        Map<String, Integer> coinPrice = bloodRequestService.coinPrice;
        mp.addAttribute("coinPrice", coinPrice);
        return userData.getRole().equalsIgnoreCase("agent") ? "agent/coins-value" : "redirect:/dashboard";
    }

    @GetMapping("dashboard/coins-report")
    public String agentCoinReport(HttpSession session, Model mp) {
        RegistrationDTO userData = (RegistrationDTO) session.getAttribute("data");
        if (userData == null) {
            return "redirect:/login";
        }
        if (userData.isNewUser()) {
            return "redirect:/update-password";
        }
        if (userData.getRole().equalsIgnoreCase("EndUser"))
            return "redirect:/dashboard";
        ArrayList<HashMap<String, Object>> list = utilityService.getAgentAndAdminCoins(userData.getUsername(), userData.getRole());
        double endUserTotalCoins = list.stream().mapToDouble(i -> (double) i.get("endUserCoins")).sum();
        double agentTotalCoins = list.stream().mapToDouble(i -> (double) i.get("agentCoins")).sum();
        double adminTotalCoins = list.stream().mapToDouble(i -> (double) i.get("adminCoins")).sum();

        mp.addAttribute("agentCoinReport", list);
        mp.addAttribute("endUserTotalCoins", endUserTotalCoins);
        mp.addAttribute("agentTotalCoins", agentTotalCoins);
        mp.addAttribute("adminTotalCoins", adminTotalCoins);
        return userData.getRole().equalsIgnoreCase("admin") ? "admin/coins-report" : "agent/coins-report";
    }
}
