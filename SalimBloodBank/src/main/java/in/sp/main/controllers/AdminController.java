package in.sp.main.controllers;

import java.util.List;

import in.sp.main.dao.UserRepository;
import in.sp.main.entities.UserModel;
import in.sp.main.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import in.sp.main.dto.RegisterDTO;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class AdminController {


    @Autowired
    LoginServiceImpl loginService;

    @Autowired
    DatabaseSetupService databaseSetupService;

    @Autowired
    RegisterServiceImpl registerService;

    @Autowired
    RegisterDTO user;

    @Autowired
    AdminService adminService;
    @Autowired
    BloodBankServiceImpl bloodBankService;

//    @Autowired
//    BloodBankServiceImpl bloodBankService;

//    @GetMapping("/admin/dashboard")
//    public String adminDashboard(Model model) {
//        // Initially, display unsorted lists
//        List<UserModel> users = (List<UserModel>) userRepository.findAll();
//        model.addAttribute("users", users);
//      //  model.addAttribute("agents", agents);
//        return "admin-dashboard";
//    }
//
//    @GetMapping("/admin/sort")
//    public String sortAdminDashboard(
//            @RequestParam("sortByUsers") String sortByUsers,
//            @RequestParam("sortByAgents") String sortByAgents,
//            Model model
//    ) {
//        // Sort users and agents based on the selected sorting option
//        List<UserModel> sortedUsers = adminService.sortUsers(sortByUsers);
//       // List<AgentModel> sortedAgents = adminService.sortAgents(sortByAgents);
//        model.addAttribute("users", sortedUsers);
//        //model.addAttribute("agents", sortedAgents);
//        return "admin-dashboard";
//    }





    @PostMapping("/agentRegForm")
    public String agentRegister(
            Model model,
            @ModelAttribute @Validated RegisterDTO registerDTO,
            BindingResult bindingResult, HttpServletRequest httpServletRequest
    )
    {
        if(bindingResult.hasErrors())
        {
            model.addAttribute("formatError",bindingResult.getFieldError().getDefaultMessage());
            return "register-page";
        }
        HttpSession session = httpServletRequest.getSession();
        RegisterDTO existingUser = (RegisterDTO) session.getAttribute("user");

//        registerDTO.setPassword(registerDTO.getDateOfBirth());
//        registerDTO.setRole("agent");
//        registerDTO.setCreatedBy("admin");
        model.addAttribute("user", user);
        model.addAttribute("signedupUsers", adminService.fetchSignedupUsers());
        if(existingUser.getRole().equalsIgnoreCase("admin")) {
            registerService.registerService(registerDTO, existingUser);
            return "admin-dashboard";
        }
        else{
            registerService.signupAgentCreatedUser(registerDTO,existingUser);
            model.addAttribute("agentUser",loginService.fetchAgentData());
            model.addAttribute("agentCoins",bloodBankService.agentCoins(user.getUsername()));
            return "agent-dashboard";
        }
    }


    @GetMapping("/admin/dashboard")
    public String adminDashboard(Model model) {
        List<RegisterDTO> users = adminService.fetchSignedupUsers();
        model.addAttribute("users", users);
        return "admin-dashboard";
    }

    @GetMapping("/sortByName")
    public String sortAdminDashboard(Model model, HttpSession session) {
//        System.out.println("><<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<,<<<<<<<<<<<<<<<<<<<");
        List<UserModel> sortedUsers = adminService.sortUsers("name");
        model.addAttribute("sortedUsers", sortedUsers);
//        System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
//        System.out.println(sortedUsers);
        return "admin-dashboard";
    }

    @GetMapping("/sortByCreatedBy")
    public String sortAdminDashboardByCreatedBy(Model model, HttpSession session) {
        List<UserModel> sortedUsers = adminService.sortUsers("CreatedBy");
        model.addAttribute("sortedUsers", sortedUsers);
        return "admin-dashboard";
    }

    @GetMapping("/sortByBloodGroup")
    public String sortAdminDashboardByBloodGroup(Model model, HttpSession session) {
        List<UserModel> sortedUsers = adminService.sortUsers("BloodGroup");
        model.addAttribute("sortedUsers", sortedUsers);
        return "admin-dashboard";
    }

    @GetMapping("/filterByAgent")
    public String filterUsers(Model model) {
        List<UserModel> filteredUsers = adminService.filterUsers("byAgent");
//        System.out.println(filteredUsers);
        model.addAttribute("filterUsers", filteredUsers);
        return "admin-dashboard";
    }

    //Blood Request
    @GetMapping("/showBloodRequests")
    public String getBloodRequests(Model model)
    {
        model.addAttribute("bloodRequests",adminService.fetchBloodRequests());
        return "bloodRequests";
    }





}
