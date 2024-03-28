package com.insightgeeks.bloodbank.controller;

import com.insightgeeks.bloodbank.dto.LoginDTO;
import com.insightgeeks.bloodbank.dto.PasswordResetDTO;
import com.insightgeeks.bloodbank.dto.SignupDTO;
import com.insightgeeks.bloodbank.service.*;
import com.insightgeeks.bloodbank.util.LoginResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class AuthenticationController {

    @Autowired
    DatabaseSetupService databaseSetupService;

    @Autowired
    BloodStockService bloodStockService;

    @Autowired
    private SignupService signupService;

    @Autowired
    private LoginService loginService;

    @Autowired
    AgentService agentService;

    @Autowired
    AdminService adminService;


    @RequestMapping("/")
    public String home() {
        databaseSetupService.setupBloodStock();
        databaseSetupService.setupDatabase();
        return "login";
    }


    @PostMapping("/userSignup")
    public String performUserSignup(@ModelAttribute @Validated SignupDTO signupDTO,
                                    BindingResult bindingResult, Model model, HttpServletRequest httpServletRequest) {

        HttpSession session = httpServletRequest.getSession();
        SignupDTO usr = (SignupDTO) session.getAttribute("user");

        if (bindingResult.hasErrors()) {

            model.addAttribute("formatError", bindingResult.getFieldError().getDefaultMessage());
            if (usr.getRole() != null && usr.getRole().equals("admin")) {
                return "createAgent";
            }

            if (usr.getRole() != null && usr.getRole().equals("agent")) {
                return "createUser";
            }

            return "signup";
        }
        try {

            signupService.signupUser(signupDTO, usr);
            if (usr != null) {
                if (usr.getRole().equals("agent")) {
                    return "agentProfilePage";
                }
                if (usr.getRole().equals("admin")) {
                    model.addAttribute("signedupUsers", loginService.fetchSignedupUsers());
                    model.addAttribute("user", usr);
                    return "adminProfilePage";
                }
            }
            return "login";
        } catch (Exception e) {
            model.addAttribute("formatError", e.getMessage());

            if (usr != null && usr.getRole().equals("admin")) {
                return "createAgent";
            }

            if (usr != null && usr.getRole().equals("agent")) {
                return "createUser";
            }

            return "signup";
        }
    }


    @PostMapping("/userLogin")
    public String performUserLogin(@ModelAttribute @Validated LoginDTO loginDTO,
                                   BindingResult bindingResult, Model model, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("formatError", bindingResult.getFieldError().getDefaultMessage());
            return "login";
        }

        try {

            LoginResult loginResult = loginService.performUserLogin(loginDTO);
            SignupDTO user = loginResult.getUser();
            HttpSession session = httpServletRequest.getSession();


            switch (loginResult.getStatus()) {
                case "success":

                    if (user.getBlockStatus().equalsIgnoreCase("blocked")) {
                        model.addAttribute("blockStatus", "User Blocked");
                        return "login";
                    }

                    session.setAttribute("user", user);
                    session.setAttribute("bloodStockList",bloodStockService.getBloodStock());
                    adminService.updateActiveUsers(user, "add");

                    switch (loginResult.getUser().getRole()) {

                        case "admin":
                            model.addAttribute("user", loginResult.getUser());
                            model.addAttribute("signedupUsers", loginService.fetchSignedupUsers());
                            session.setAttribute("totalCoins",adminService.calculateTotalCoins());
                            session.setAttribute("totalCommission",adminService.getAllAgentCoins());
                            session.setAttribute("totalUserCoins",adminService.getAllUsersPoints());

                            return "adminProfilePage";

                        case "agent":
                            session.setAttribute("totalCoins",agentService.calculateTotalPointsForAgentCreatedUsers(user));
                            session.setAttribute("totalCommission",agentService.calculateCommissionEarned(user));
                            session.setAttribute("totalUserCoins",agentService.calculateUsersActualPoints(user));
                            session.setAttribute("bloodRequestSummaryList",agentService.getBloodRequestSummaryForAgent(user));
                            model.addAttribute("user", loginResult.getUser());
                            return "agentProfilePage";

                        case "user":
                            model.addAttribute("user", loginResult.getUser());
                            return "endUserProfilePage";
                    }

                case "reset":

                    session.setAttribute("user",user);
                    return "passwordReset";

                case "invalid":

                    model.addAttribute("status", "Invalid credentials");
                    if (loginResult.getBlockStatus() == 1) {
                        model.addAttribute("blockStatus", "User Blocked");
                    } else if (loginResult.getBlockStatus() == 2) {
                        model.addAttribute("blockStatus", "User cannot be blocked because user does not exist");
                    }
                    return "login";
            }
        } catch (Exception e) {

            model.addAttribute("formatError", e.getMessage());
            return "login";
        }
        return "";
    }


    @PostMapping(value = "/changePassword")
    public String changePassword(@ModelAttribute @Validated PasswordResetDTO passwordResetDTO, Model model,
                                 BindingResult bindingResult, @RequestParam String actualName) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("formarError", "Please fill all fields properly");
            return "passwordReset";
        }


        switch (loginService.updatePassword(actualName, passwordResetDTO)) {
            case "resetSuccess":

                model.addAttribute("passwordResetStatus", "Password successfully changed");
                return "login";

            case "unmatchedPassword":

                model.addAttribute("passwordResetStatus", "Passwords do not match");
                return "passwordReset";

            case "missingUser":

                model.addAttribute("passwordResetStatus", "User does not exist");
                return "passwordReset";
        }
        return "";
    }


    @RequestMapping("/logout")
    public String logout(HttpServletRequest httpServletRequest) {

        HttpSession session = httpServletRequest.getSession();
        SignupDTO usr = (SignupDTO) session.getAttribute("user");
        adminService.updateActiveUsers(usr, "remove");
        session.invalidate();

        return "login";
    }
}
