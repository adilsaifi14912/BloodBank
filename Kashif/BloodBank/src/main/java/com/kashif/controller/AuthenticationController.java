package com.kashif.controller;

import com.kashif.dto.LoginDTO;
import com.kashif.dto.RegistrationDTO;
import com.kashif.service.UtilityService;
import com.kashif.service.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;


@Controller
public class AuthenticationController {
    private int loginAttempts = 0;
    @Autowired
    private UtilityService utilityService;

    @Autowired
    private UserRegistrationService userRegistrationService;

    // ----- SignUp Page ----
    @GetMapping("/signup")
    public String signup(HttpSession session) {
        RegistrationDTO userData = (RegistrationDTO) session.getAttribute("data");
        if (userData != null) return "redirect:/dashboard";
        return "signup";

    }

    // Users get Registered and Success Message

    @PostMapping("/registration")
    public String endUserRegistration(RegistrationDTO registrationDTO, HttpSession session, Model mp) {
        RegistrationDTO userData = (RegistrationDTO) session.getAttribute("data");
        if (userData != null) {
            return "redirect:/dashboard";
        }
        if (registrationDTO.getUsername() == null || registrationDTO.getDob() == null || registrationDTO.getPassword() == null || registrationDTO.getBloodGroup() == null || registrationDTO.getName() == null) {
            mp.addAttribute("errorMsg", "Fields can not be null");
            return "signup";
        }
        if (registrationDTO.getUsername().isBlank() || registrationDTO.getDob().isBlank() || registrationDTO.getPassword().isBlank() || registrationDTO.getBloodGroup().isBlank() || registrationDTO.getName().isBlank()) {
            mp.addAttribute("errorMsg", "Fields can not be Blank");
            return "signup";
        }
        if (userRegistrationService.existsByUsername(registrationDTO.getUsername())) {
            mp.addAttribute("errorMsg", "Username Already Exists");
            return "signup";
        }

        RegistrationDTO registeredUser = userRegistrationService.registerUser(registrationDTO);
        mp.addAttribute("success", "Account Created Successfully");
        return "login";

    }


    // ------ Login Page -----
    @GetMapping("/login")
    public String login(HttpSession session) {

        RegistrationDTO userData = (RegistrationDTO) session.getAttribute("data");
        if (userData != null) return "redirect:/dashboard";
        return "login";
    }


    //----- Got the Login details and redirected to Dashboard -----
    @PostMapping("/login")
    public String loginChk(LoginDTO loginDTO, HttpSession session, Model mp) {
        RegistrationDTO userData = (RegistrationDTO) session.getAttribute("data");
        if (userData != null) return "redirect:/dashboard";
        session.setAttribute("loginAttempts", loginAttempts);
        String username = loginDTO.getUsername();
        String password = loginDTO.getPassword();

        if (username == null || password == null || username.isBlank() || password.isBlank()) {
            mp.addAttribute("errorMsg", "Fields can not be blank");
            return "login";
        }
        if (!userRegistrationService.existsByUsername(username)) {
            mp.addAttribute("errorMsg", "Login failed: Username does not exist");
            return "login";
        }
        if (userRegistrationService.getBlockedStatusByUsername(username)) {
            mp.addAttribute("errorMsg", "User BLOCKED ");
            return "login";
        }

        String pass = userRegistrationService.getPasswordByUsername(username);
        if (pass.equals(password)) {
            session.setAttribute("loginAttempts", 0);
            loginAttempts = 0;
            RegistrationDTO user = userRegistrationService.getUserByUsername(username).get();
            session.setAttribute("data", user);
            utilityService.updateActiveUsers(user.getUsername(), "add");
            return "redirect:/dashboard";
        } else {
            loginAttempts++;
        }

        if (loginAttempts > 3) {
            userRegistrationService.updateBlockedStatusByUsername(true, username);
            session.invalidate(); // Invalidate the session to clear cache
            mp.addAttribute("errorMsg", "User BLOCKED ");
            return "login";
        }
        mp.addAttribute("errorMsg", "Invalid Password ");
        mp.addAttribute("times", loginAttempts);
        return "login";
    }


    // ------ Password Update Page ------
    @GetMapping("/update-password")
    public String changePassword(HttpSession session, Model mp) {
        RegistrationDTO userData = (RegistrationDTO) session.getAttribute("data");
        if (userData == null) {
            mp.addAttribute("errorMsg", "You are not logged In");
            return "redirect:/login";
        }
        return "update-password";
    }

    // ----- Updating Password and changing the newUser to False -----
    @PostMapping("/update-password")
    public String validatePassword(@RequestParam String currentPassword, @RequestParam String newPassword, @RequestParam String confirmPassword, HttpSession session, Model mp) {
        RegistrationDTO userData = (RegistrationDTO) session.getAttribute("data");
        if (userData == null) {
            mp.addAttribute("errorMsg", "You are not logged In");
            return "redirect:/login";
        }
        if(currentPassword==null || confirmPassword.isBlank() || newPassword==null || newPassword.isBlank()){
            mp.addAttribute("errorMsg", "Field Can't be Empty");
            return "update-password";
        }
        if (!newPassword.equals(confirmPassword)) {
            mp.addAttribute("errorMsg", "Password didn't Match.");
            return "update-password";
        }
        if (currentPassword.equals(newPassword)) {
            mp.addAttribute("errorMsg", "Can't set old password");
            return "update-password";
        }
        if (!currentPassword.equals(userData.getPassword())) {
            mp.addAttribute("errorMsg", "Wrong Password");
            return "update-password";
        }

        String username = userData.getUsername();

        //---------- Updated the Password ---------
        userRegistrationService.updatePasswordByUsername(newPassword, username);
        userRegistrationService.updateNewUserByUsername(false, username);
        RegistrationDTO user = (RegistrationDTO) session.getAttribute("data");
        utilityService.updateActiveUsers(user.getUsername(), "remove");
        session.invalidate();
        mp.addAttribute("success", "Password updated successfully.");
        return "login";
    }

    //----- After redirecting to Dashboard -----
    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model mp) {
        RegistrationDTO userData = (RegistrationDTO) session.getAttribute("data");
        if (userData == null) {
            return "redirect:/login";
        }
        ;
        if (userData.isNewUser()) {
            return "redirect:/update-password";
        }
        ArrayList<HashMap<String, Object>> list = utilityService.getAgentAndAdminCoins(userData.getUsername(), userData.getRole());
        double agentTotalCoins = list.stream().mapToDouble(i -> (double) i.get("agentCoins")).sum();
        double adminTotalCoins = list.stream().mapToDouble(i -> (double) i.get("adminCoins")).sum();
        if (userData.getRole().equalsIgnoreCase("admin")) {
            mp.addAttribute("allUsers", userRegistrationService.getAllUsers());
            mp.addAttribute("adminTotalCoins", adminTotalCoins);
            return "admin/dashboard";
        }
        if (userData.getRole().equals("agent")) {
            mp.addAttribute("agentTotalCoins", agentTotalCoins);
            return "agent/dashboard";
        }
        mp.addAttribute("totalCoins", utilityService.getEndUserCoins(userData.getUsername()));
        return "enduser/dashboard";
    }


//    -------------------- Here Different Task: 14 Mar 2024-------------------

    @GetMapping("/dashboard/agent-account-creation")
    public String agentRegistration(HttpSession session, Model mp) {
        RegistrationDTO userData = (RegistrationDTO) session.getAttribute("data");
        if (userData == null) {
            mp.addAttribute("errorMsg", "You are not logged In");
            return "redirect:/login";
        }
        if (userData.isNewUser()) {
            return "redirect:/update-password";
        }
        if (!userData.getRole().equalsIgnoreCase("admin")) {
            mp.addAttribute("errorMsg", "Not Allowed (You are not Admin)");
            return "redirect:/dashboard";
        }

        return "admin/agent-signup";
    }

    @PostMapping("/dashboard/agent-account-creation")
    public String agentRegistrationSubmit(RegistrationDTO registrationDTO, HttpSession session, Model mp) {
        RegistrationDTO userData = (RegistrationDTO) session.getAttribute("data");
        if (userData == null) {
            mp.addAttribute("errorMsg", "You are not logged In");
            return "redirect:/login";
        }
        if (userData.isNewUser()) {
            return "redirect:/update-password";
        }
        if (!userData.getRole().equalsIgnoreCase("admin")) {
            return "redirect:/dashboard";
        }
        if( registrationDTO.getUsername()==null || registrationDTO.getDob() ==null || registrationDTO.getPassword()==null || registrationDTO.getBloodGroup()==null || registrationDTO.getName()==null){
            mp.addAttribute("errorMsg", "Fields can not be null");
            return "admin/agent-signup";
        }
        if (registrationDTO.getUsername().isBlank() || registrationDTO.getDob().isBlank() || registrationDTO.getPassword().isBlank() || registrationDTO.getBloodGroup().isBlank() || registrationDTO.getName().isBlank()) {
            mp.addAttribute("errorMsg", "Fields can not be blank");
            return "admin/agent-signup";
        }
        if (registrationDTO.getCommission() > 70) {
            mp.addAttribute("errorMsg", "Maximum Commission should be 70 %");
            return "admin/agent-signup";
        }

        if (userRegistrationService.existsByUsername(registrationDTO.getUsername())) {
            mp.addAttribute("errorMsg", "Username Already Exists");
            return "admin/agent-signup";
        }

        registrationDTO.setPassword(registrationDTO.getDob());
        registrationDTO.setBloodGroup("-");
        registrationDTO.setCreatedBy("admin");
        registrationDTO.setRole("agent");
        userRegistrationService.registerUser(registrationDTO);
        mp.addAttribute("success", "Account Created Successfully");
        return "admin/agent-signup";
    }

    @GetMapping("/dashboard/enduser-account-creation")
    public String agentDashboard(HttpSession session, Model mp) {
        RegistrationDTO userData = (RegistrationDTO) session.getAttribute("data");
        if (userData == null) {
            mp.addAttribute("errorMsg", "You are not logged In");
            return "redirect:/login";
        }
        if (userData.isNewUser()) {
            return "redirect:/update-password";
        }
        if (userData.getRole().equalsIgnoreCase("EndUser")) {
            return "redirect:/dashboard";
        }
        return "agent/enduser-signup";
    }

    @PostMapping("/dashboard/enduser-account-creation")
    public String endUserRegistrationByAgent(RegistrationDTO registrationDTO, HttpSession session, Model mp) {

        RegistrationDTO userData = (RegistrationDTO) session.getAttribute("data");

        if (userData == null) {
            mp.addAttribute("errorMsg", "You are not logged In");
            return "redirect:/login";
        }
        if (userData.isNewUser()) {
            return "redirect:/update-password";
        }
        if (userData.getRole().equalsIgnoreCase("EndUser")) {
            return "redirect:/dashboard";
        }
        if( registrationDTO.getUsername()==null || registrationDTO.getDob() ==null || registrationDTO.getPassword()==null || registrationDTO.getBloodGroup()==null || registrationDTO.getName()==null){
            mp.addAttribute("errorMsg", "Fields can not be null");
            return "agent/enduser-signup";
        }
        if (registrationDTO.getUsername().isBlank() || registrationDTO.getDob().isBlank() || registrationDTO.getPassword().isBlank() || registrationDTO.getBloodGroup().isBlank() || registrationDTO.getName().isBlank()) {
            mp.addAttribute("errorMsg", "Fields can not be blank");
            return "agent/enduser-signup";
        }
        if (userRegistrationService.existsByUsername(registrationDTO.getUsername())) {
            mp.addAttribute("errorMsg", "Username Already Exists");
            return "agent/enduser-signup";
        }
        registrationDTO.setCreatedBy(userData.getUsername());
        registrationDTO.setPassword(registrationDTO.getDob());

        userRegistrationService.registerUser(registrationDTO);
        mp.addAttribute("success", "Account created successfully");
        return "agent/enduser-signup";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        RegistrationDTO user = (RegistrationDTO) session.getAttribute("data");
        if (user == null) return "redirect:/login";
        utilityService.updateActiveUsers(user.getUsername(), "remove");
        session.invalidate();
        return "redirect:/login";
    }
}
