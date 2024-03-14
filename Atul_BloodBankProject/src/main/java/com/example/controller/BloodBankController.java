package com.example.controller;

import com.example.dto.UserLoginDto;
import com.example.dto.UserRegisterDto;
import com.example.service.AdminService;
import com.example.service.LoginService;
import com.example.service.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class BloodBankController {
    @Autowired
    SignupService signupService;
    @Autowired
    LoginService loginService;

    @RequestMapping("/")
    public String showHome() {
        return "home";
    }

    @RequestMapping("/signup")
    public String signUp(HttpServletRequest request,Model model) {
        model.addAttribute("role",request.getSession().getAttribute("role"));
        return "signup";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping(value = "/register")
    public String register(@ModelAttribute @Valid UserRegisterDto userRegisterDto, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        String role = (String) request.getSession().getAttribute("role");
        if (role!=null) {
            userRegisterDto.setPassword(String.valueOf(userRegisterDto.getDob()));
            userRegisterDto.setRole(role.equalsIgnoreCase("ADMIN") ? "AGENT" : "ENDUSER");
            userRegisterDto.setCreatedBy((String) request.getSession().getAttribute("userId"));

        }
        else {
            userRegisterDto.setRole("ENDUSER");
            userRegisterDto.setCreatedBy("auto");
        }
        if (signupService.addUser(userRegisterDto)) {
            model.addAttribute("successMessage", "UserName already exits ,Please try again with different username");
        }
        else {
            model.addAttribute("successMessage", "Successfully registered!");
        }
        redirectAttributes.addFlashAttribute("successMessage", "Successfully Registered!");
        String referer = request.getHeader("referer");
        System.out.println(referer);
        if (referer != null) {
            return "redirect:" + referer;
        }
        else {
            return "redirect:/"; // Redirect to home page if referer is not available
        }
    }

    @PostMapping(value = "/userLogin")
    public String userLogin(@ModelAttribute @Valid UserLoginDto userLoginDto, Model model, HttpServletRequest request) {
        UserRegisterDto returnDto = loginService.checkUser(userLoginDto);

        if (returnDto != null) {
//            sucessfully login
            HttpSession session = request.getSession();   //session object created
            System.out.println("session created");
            session.setAttribute("userId", returnDto.getUserName());
            session.setAttribute("role", returnDto.getRole());
            if (returnDto.isFirstLogin()) {
                // Redirect to the password update page
                return "updatePasswordForm";
            }
            if (returnDto.isLocked()) {
                return "loginAttemptsExceeded";
            }
            else {
                //switch for matching corresponding role and display view according with role
                switch (returnDto.getRole()) {
                    case "ADMIN":
                        model.addAttribute("dto", returnDto);
                        model.addAttribute("signupUsers", loginService.fetchSignedupUsers());
                        return "admin";
                    case "AGENT":
                        model.addAttribute("dto", returnDto);
                        return "user";
                    case "EndUser":
                        model.addAttribute("dto", returnDto);
                        return "user";
                    default:
                        return "loginError";

                }
            }
        }
        return "loginError";

    }

    @PostMapping(value = "/processUpdatePassword")
    public String updatePassword(@ModelAttribute @Valid UserLoginDto userLoginDto) {
        loginService.updatePassword(userLoginDto);
        return "passwordUpdateSuccess";
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        System.out.println("session invalid");
        return "login";
    }

    @RequestMapping(value = "/createagent")
    public String createAgent() {
        return "createagent";
    }
//    @PreDestroy
//    void destroy(){
//        HttpSession session = request.getSession();
//        session.invalidate();
//        System.out.println("session invalid by destroy");
//    }

}
