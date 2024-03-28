package com.BloodBank.Controller;
import com.BloodBank.Model.BloodStockModel;
import com.BloodBank.Model.UserModel;
import com.BloodBank.Service.BloodRequestService;
import com.BloodBank.Service.BloodStockService;
import com.BloodBank.Service.UserService;
import com.BloodBank.dto.UserLoginDTO;
import com.BloodBank.dto.UserSignUpDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class LoginSignUpController {

    private int loginAttempt =0;
    @Autowired
    private UserService userService;

    @Autowired
    private HttpSession session;

    @Autowired
    private BloodRequestService bloodRequestService;

    @Autowired
    private BloodStockService bloodStockService;

    @PostMapping("/addUser")
    public String addUser(UserSignUpDTO userSignUpDTO, Model model) {
        // Check if the username already exists
        if (userService.isUsernameTaken(userSignUpDTO.getUsername())) {
            // Add error message to the model
            model.addAttribute("usernameError", "Username is already taken. Please choose a different username.");
            // Return the same view with the error message
            return "signup";
        }

        // If the username is not taken, proceed with adding the user
        userService.addUser(userSignUpDTO, "EU", "auto");
        return "login";
    }

    @PostMapping("/loginUser")
    public String loginUser(UserLoginDTO userLoginDTO, Model model,@RequestParam(required = false, defaultValue = "id") String sortBy,@RequestParam(required = false,defaultValue ="") String filterBy) {
        UserModel userModel=userService.authenticateUser(userLoginDTO.getUsername());
        if(userModel==null){
            model.addAttribute("errorMsg", "Username does not exists");
            return "login";
        }
        if(userLoginDTO.getPassword().equals(userModel.getPassword())){
            session.setAttribute("userLoggedIn",userModel);
            if (userModel.isFirstTimeLogin()) {
                return "firstLogin";
            } else if (userModel.getBlockedStatus()) {
                model.addAttribute("errorMsg", "USER BLOCKED");
                return "login";
            } else{
                session.setAttribute("userModel", userModel); // Set user model in session
                switch (userModel.getRole()) {
                    case "AD":
                        List<UserModel> userModelList = userService.getAllUsers(sortBy,filterBy);
                        model.addAttribute("userModelList", userModelList);
                        return "adminDashboard";
                    case "EU":
                        return "endUserDashboard";
                    case "AG":
                        return "agentDashboard";
                    default:
                        return "signup";
                }
            }
        }
        else {
            loginAttempt++;
            if (loginAttempt < 3) {
                model.addAttribute("errorMsg", "Invalid Password");
            } else {
                userService.updateBlockedStatus(true,userModel.getUsername());
                model.addAttribute("errorMsg", "You are Blocked!!");
            }
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "login";
    }

    @PostMapping("/userFirstLogin")
    public String updatePassword(@RequestParam String username,
                                 @RequestParam String newPassword) {
        userService.updateUserPassword(newPassword, username);
        return "login";
    }

    @RequestMapping("/createAgent")
    public String createAgent() {
        return "createAgentForm";
    }

    @RequestMapping("/createUser")
    public String createUser()
    {
        return "createUserForm";
    }

    @PostMapping("/userCreated")
    public String userCreated(UserSignUpDTO userSignUpDTO, Model model, RedirectAttributes redirectAttributes)
    {
        // Check if the username already exists
        if (userService.isUsernameTaken(userSignUpDTO.getUsername())) {
            // Add error message to the model
            model.addAttribute("usernameError", "Username is already taken. Please choose a different username.");
            // Return the same view with the error message
            return "createUserForm";
        }

        // If the username is not taken, proceed with adding the user
        userService.addUser(userSignUpDTO, "EU", ((UserModel) session.getAttribute("userLoggedIn")).getUsername());

        // Add success message to the model
        model.addAttribute("successMessage", "Agent created successfully!");

        return "createUserForm";

    }
    @PostMapping("/agentCreated")
    public String agentCreated(UserSignUpDTO userSignUpDTO,Model model)
    {
        // Check if the username already exists
        if (userService.isUsernameTaken(userSignUpDTO.getUsername())) {
            // Add error message to the model
            model.addAttribute("usernameError", "Username is already taken. Please choose a different username.");
            // Return the same view with the error message
            return "createAgentForm";
        }
        userService.addUser(userSignUpDTO,"AG","admin");
        model.addAttribute("successMessage", "Agent created successfully!");
        return "createAgentForm";
    }

    @RequestMapping("/sortFilter")
    public String sortFilter(Model model,
                             @RequestParam(required = false, defaultValue = "id") String sortBy,
                             @RequestParam(required = false, defaultValue = "") String filterBy,
                             @RequestParam(required = false) String startDate,
                             @RequestParam(required = false) String endDate,
                             @RequestParam(required = false) String username
                        )
    {
        List<UserModel> userModelList = userService.getAllUsers(sortBy,filterBy);
        model.addAttribute("userModelList", userModelList);
        return "sortFilter";
    }
    @GetMapping("/sortByAgent")
    public String sortAgentUser(Model model,@RequestParam(required = false, defaultValue = "id") String sortBy){
        List<UserModel> agentuserList = userService.getAllUsersByAgent(((UserModel)session.getAttribute("userLoggedIn")).getUsername(),sortBy);
        model.addAttribute("userModelList", agentuserList);
        return "sortFilter";
    }

    @GetMapping("/sortAndFilter")
    public String sortedList(Model model,@RequestParam(required = false, defaultValue = "id") String sortBy,@RequestParam(required = false,defaultValue ="") String filterBy){
        List<UserModel> userModelList = userService.getAllUsers(sortBy, filterBy);
        model.addAttribute("userModelList", userModelList);
        return "sortFilter";
    }

    @PostMapping("/addRemark")
    public String addRemark(@RequestParam Long requestId,@RequestParam String remark) {
        bloodRequestService.addRemarkToRequest(requestId,remark);
        return "adminDashboard";
    }

    @RequestMapping("/adminBloodReport")
    public String adminBloodReport(Model model)
    {
        List<Map<String, Object>> bloodReport = bloodRequestService.getAdminBloodReport();
        model.addAttribute("bloodReport", bloodReport);
        return "bloodReport";
    }

    @RequestMapping("/bloodAvailable")
    public String bloodAvailable(Model model)
    {
        List<BloodStockModel> bloodStockList = bloodStockService.getBloodStock();
        model.addAttribute("bloodStockList", bloodStockList);
        return "bloodAvailable";
    }

    @RequestMapping("/adminCoinReport")
    public String adminCoinReport(Model model)
    {
        List<Map<String, Object>> coinReport = bloodRequestService.getAdminCoinReport();
        model.addAttribute("coinReport", coinReport);
        return "coinReport";
    }

    @RequestMapping("/agentBloodReport")
    public String agentBloodReport(Model model)
    {

        List<Map<String, Object>> bloodReport = bloodRequestService.getAgentBloodReport(((UserModel)session.getAttribute("userLoggedIn")).getUsername());
        model.addAttribute("bloodReport", bloodReport);
        return "bloodReport";
    }

    @RequestMapping("/agentCoinReport")
    public String agentCoinReport(Model model)
    {
        List<Map<String, Object>> coinReport = bloodRequestService.getAgentCoinReport(((UserModel)session.getAttribute("userLoggedIn")).getUsername());
        model.addAttribute("coinReport", coinReport);
        return "coinReport";
    }

    @RequestMapping("/agentCoinsByRate")
    public String getAgentCoinsByRate(Model model)
    {
        List<Map<String, Object>> agentCoinsByRate = bloodRequestService.getAgentCoinsByRate(((UserModel)session.getAttribute("userLoggedIn")).getUsername());
        model.addAttribute("agentCoinsByRate", agentCoinsByRate);
        return "agentCoinsByRate";
    }
}