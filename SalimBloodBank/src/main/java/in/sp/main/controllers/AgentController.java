package in.sp.main.controllers;

import in.sp.main.dto.RegisterDTO;
import in.sp.main.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AgentController {

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

    @PostMapping("/createUserByAgent")
    public String createEndUserByAgent(@ModelAttribute @Validated RegisterDTO registerDTO,
                                       HttpServletRequest httpServletRequest, Model model)

    {
        HttpSession session = httpServletRequest.getSession();
        RegisterDTO user = (RegisterDTO) session.getAttribute("user");
        try{

            registerService.signupAgentCreatedUser(registerDTO,user);
//            System.out.println(user.getUsername());
            return "agent-dashboard";
        }catch (Exception e)
        {
            e.printStackTrace();
            return "endUserRegisterByAgent";
        }

//        @GetMapping("/adm")
//        public String agentUser( ) {
//        List<RegisterDTO> users = loginService.getAgentUsers();
//        model.addAttribute("users", users);
//        return "agent";
//    }

    }

    //blood request by user created by agent
    @GetMapping("/bloodRequestsAgentDashboard")
    public String getApprovedRequests( Model model, HttpServletRequest httpServletRequest)
    {
        HttpSession session = httpServletRequest.getSession();
        RegisterDTO agent = (RegisterDTO) session.getAttribute("user");

        model.addAttribute("bloodRequestByUser",bloodBankService.bloodRequestUserAgent(agent.getUsername()));
//        System.out.println(bloodBankService.bloodRequestUserAgent(agent.getUsername()));
        return "agent-dashboard-user-request";
    }

}
