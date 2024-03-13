package com.kashif.service;

import com.kashif.dto.LoginDTO;
import com.kashif.dto.RegistrationDTO;
import com.kashif.entity.UserRegistration;
import com.kashif.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.*;

@Service
public class UserRegistrationService {
    private int loginAttempts = 0;
    @Autowired
    private UserRegistration userRegistration;

    @Autowired
    private UserRepo userRepo;


    public RegistrationDTO registerUser(RegistrationDTO registrationDTO) {

        if (registrationDTO.getUsername() != null && !registrationDTO.getUsername().isEmpty() && !userRepo.existsByUsername(registrationDTO.getUsername())) {
            userRegistration.setUsername(registrationDTO.getUsername());
        } else {
            throw new RuntimeException("Username might be  null or Already Exists");
        }
        if (registrationDTO.getName() != null && !registrationDTO.getName().isEmpty()) {
            userRegistration.setName(registrationDTO.getName());
        } else {
            throw new RuntimeException("Name Can't be null");
        }
        if (registrationDTO.getEmail() != null && !registrationDTO.getEmail().isEmpty()) {
            userRegistration.setEmail(registrationDTO.getEmail());
        } else {
            throw new RuntimeException("Email Can't be null");
        }
        if (registrationDTO.getAddress() != null && !registrationDTO.getAddress().isEmpty()) {
            userRegistration.setAddress(registrationDTO.getAddress());
        } else {
            throw new RuntimeException("Address Can't be null");
        }
        if (registrationDTO.getPassword() != null && !registrationDTO.getPassword().isEmpty()) {
            userRegistration.setPassword(registrationDTO.getPassword());
            registrationDTO.setPassword("*******");
        } else {
            throw new RuntimeException("Password Can't be null");
        }

        if (registrationDTO.getDob() != null && !registrationDTO.getDob().isEmpty()) {
            userRegistration.setDob(LocalDate.parse(registrationDTO.getDob()));

        } else {
            throw new RuntimeException("DOB Can't be null");
        }
        if (registrationDTO.getBloodGroup() != null && !registrationDTO.getBloodGroup().isEmpty()) {
            userRegistration.setBloodGroup(registrationDTO.getBloodGroup());
        } else {
            throw new RuntimeException("Blood Group Can't be null");
        }

        userRegistration.setId(0);
        userRegistration.setRole("EndUser");
        userRegistration.setCreatedBy("-");
        userRegistration.setCreationTime(new Date());
        userRegistration.setUpdatedTime(new Date());
        userRegistration.setModifyBy("-");
        //--------------- Extra fields Default data -------
        userRegistration.setNewUser(true);
        userRegistration.setBlockedStatus(false);

        userRepo.save(userRegistration);
        return registrationDTO;
    }

    public boolean existsByUsernamer(String username) {
        return userRepo.existsByUsername(username);
    }

    public String loginCheck(LoginDTO loginDTO, HttpSession session, Model model) {
        session.setAttribute("loginAttempts", loginAttempts);

        String username = loginDTO.getUsername();
        String password = loginDTO.getPassword();
        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            model.addAttribute("errorMsg", "Login failed: Invalid Username ");
            return "login";
        }
        if (!userRepo.existsByUsername(username)) {
            model.addAttribute("errorMsg", "Login failed: Invalid Username ");
            return "login";
        }
        if (userRepo.getBlockedStatusByUsername(username)) {
            model.addAttribute("errorMsg", "User BLOCKED ");
            return "login";
        }

        String pass = userRepo.getPasswordByUsername(username);
        if (pass.equals(password)) {
            session.setAttribute("data", userRepo.findByUsername(username).get());
            return "redirect:/dashboard";
        } else {
            loginAttempts++;
        }

        if (loginAttempts > 3) {
            userRepo.updateBlockedStatusByUsername(true, username);
            session.invalidate(); // Invalidate the session to clear cache
            model.addAttribute("errorMsg", "Login failed: User BLOCKED ");

            return "login";
        }
        model.addAttribute("errorMsg", "Login failed: Invalid Password ");
        model.addAttribute("times", loginAttempts);
        return "login";
    }

    public String validateLogin(String currentPassword, String newPassword, String confirmPassword, Model mp, HttpSession session) {
        UserRegistration userData = (UserRegistration) session.getAttribute("data");
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
        System.out.println(userData.getUsername());
        //---------- Updated the Password ---------
        userRepo.updatePasswordByUsername(newPassword, username);
        userRepo.updateNewUserByUsername(false, username);
        mp.addAttribute("success", "Password updated successfully.");
        return "login";
    }

    public String dashboard(Model mp, HttpSession session) {
        UserRegistration userData = (UserRegistration) session.getAttribute("data");
        mp.addAttribute("data", userData);
        if (userData.isNewUser()) {
            return "redirect:/update-password";
        }
        if (userData.getRole().equalsIgnoreCase("admin")) {

            mp.addAttribute("allUsers", userRepo.findAllEndUsers());
            return "admin-dashboard";
        }
        if (userData.getRole().equals("agent")) return "agent-dashboard";
        return "enduser-dashboard";
    }


}
