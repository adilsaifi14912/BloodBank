package com.kashif.controller;
import com.kashif.dto.LoginDTO;
import com.kashif.dto.RegistrationDTO;
import com.kashif.entity.UserRegistration;
import com.kashif.service.UtilityService;
import com.kashif.service.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;


@Controller
public class AuthenticationController {
    private int loginAttempts = 0;
    @Autowired
    private UtilityService sessionService;
    @Autowired
    private UserRegistrationService userRegistrationService;

    // ----- SignUp Page ----
    @GetMapping("/signup")
    public String signup(HttpSession session) {
        RegistrationDTO userData = (RegistrationDTO) session.getAttribute("data");
        if(userData!=null) return "redirect:/dashboard";
        return "signup";

    }

    // Users get Registered and Success Message

    @PostMapping("/registration")
    public String endUserRegistration(RegistrationDTO registrationDTO, HttpSession session, Model mp) {
        RegistrationDTO userData = (RegistrationDTO) session.getAttribute("data");
        if(userData!=null) {
            return "redirect:/dashboard";
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
        if(userData!=null) return "redirect:/dashboard";
        return "login";
    }


    //----- Got the Login details and redirected to Dashboard -----
    @PostMapping("/login")
    public String loginChk(LoginDTO loginDTO, HttpSession session, Model mp) {
        RegistrationDTO userData = (RegistrationDTO) session.getAttribute("data");
        if(userData!=null) return "redirect:/dashboard";
        mp.addAttribute("errorMsg");
        session.setAttribute("loginAttempts", loginAttempts);
        String username = loginDTO.getUsername();
        String password = loginDTO.getPassword();

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
            RegistrationDTO user = userRegistrationService.findByUsername(username).get();
            session.setAttribute("data", user);
            sessionService.updateActiveUsers(user, "add");
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
        if(userData==null){
            mp.addAttribute("errorMsg", "You are not logged In");
            return "redirect:/login";
        }
        return "update-password";
    }

    // ----- Updating Password and changing the newUser to False -----
    @PostMapping("/update-password")
    public String validatePassword(@RequestParam String currentPassword, @RequestParam String newPassword, @RequestParam String confirmPassword, HttpSession session, Model mp) {
        RegistrationDTO userData = (RegistrationDTO) session.getAttribute("data");
        if(userData==null){
            mp.addAttribute("errorMsg", "You are not logged In");
            return "redirect:/login";
        };
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
        session.removeAttribute("data");
        mp.addAttribute("success", "Password updated successfully.");
        System.out.println("Update horha huu ");
        return "login";
    }

    //----- After redirecting to Dashboard -----
    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model mp) {
        RegistrationDTO userData = (RegistrationDTO) session.getAttribute("data");
        if(userData==null){
            return "redirect:/login";
        };

        if (userData.isNewUser()) {
            return "redirect:/update-password";
        }
        if (userData.getRole().equalsIgnoreCase("admin")) {
            mp.addAttribute("allUsers", userRegistrationService.findAllEndUsers());
            return "admin/dashboard";
        }
        if (userData.getRole().equals("agent"))
            return "agent/dashboard";
        return "enduser/dashboard";
    }


//    -------------------- Here Different Task: 14 Mar 2024-------------------

    @GetMapping("/dashboard/agent-account-creation")
    public String agentRegistration(HttpSession session, Model mp){
        RegistrationDTO userData = (RegistrationDTO) session.getAttribute("data");
        if(userData==null){
            mp.addAttribute("errorMsg", "You are not logged In");
            return "redirect:/login";
        }
        if (userData.isNewUser()) {
            return "redirect:/update-password";
        }
        if(!userData.getRole().equalsIgnoreCase("admin") ){
            mp.addAttribute("errorMsg", "Not Allowed (You are not Admin)");
            return "redirect:/dashboard";
        }

        return "admin/agent-signup";
    }
    @PostMapping("/dashboard/agent-account-creation")
    public String agentRegistrationSubmit(RegistrationDTO registrationDTO, HttpSession session, Model mp){
        RegistrationDTO userData = (RegistrationDTO) session.getAttribute("data");
        if(userData==null ){
            mp.addAttribute("errorMsg", "You are not logged In");
            return "redirect:/login";
        }
        if (userData.isNewUser()) {
            return "redirect:/update-password";
        }
         if(!userData.getRole().equalsIgnoreCase("admin") ){
            mp.addAttribute("errorMsg", "Not Allowed (You are not Admin)");
            return "redirect:/dashboard";
        }
        if (userRegistrationService.existsByUsername(registrationDTO.getUsername())) {
            mp.addAttribute("errorMsg", "Username Already Exists");
            return "admin/agent-signup";
        }
        registrationDTO.setPassword(registrationDTO.getDob().toString());
        registrationDTO.setBloodGroup("-");
        registrationDTO.setCreatedBy("admin");
        registrationDTO.setRole("agent");
        userRegistrationService.registerUser(registrationDTO);
        mp.addAttribute("success", "Account Created Successfully");
        return "admin/agent-signup";
    }

    @GetMapping("/dashboard/enduser-account-creation")
    public String agentDashboard(HttpSession session, Model mp){
        RegistrationDTO userData = (RegistrationDTO) session.getAttribute("data");
        if(userData==null){
            mp.addAttribute("errorMsg", "You are not logged In");
            return "redirect:/login";
        }
        if (userData.isNewUser()) {
            return "redirect:/update-password";
        }
        return "agent/enduser-signup";
    }
    @PostMapping("/dashboard/enduser-account-creation")
    public String endUserRegistrationByAgent(RegistrationDTO registrationDTO, HttpSession session, Model mp){

        RegistrationDTO userData = (RegistrationDTO) session.getAttribute("data");

        if(userData==null){
            mp.addAttribute("errorMsg", "You are not logged In");
            return "redirect:/login";
        }
        if (userData.isNewUser()) {
            return "redirect:/update-password";
        }
        if (userRegistrationService.existsByUsername(registrationDTO.getUsername())) {
            mp.addAttribute("errorMsg", "Username Already Exists");
            return "agent/enduser-signup";
        }
        registrationDTO.setCreatedBy(userData.getUsername());
        registrationDTO.setPassword(registrationDTO.getDob().toString());

        userRegistrationService.registerUser(registrationDTO);
        mp.addAttribute("success", "Account created successfully");
        return "agent/enduser-signup";
    }
    @GetMapping("/logout")
    public String logout(HttpSession session){
        RegistrationDTO user = (RegistrationDTO) session.getAttribute("data");
        sessionService.updateActiveUsers(user, "remove");
        session.invalidate();

        return "redirect:/login";
    }
}
