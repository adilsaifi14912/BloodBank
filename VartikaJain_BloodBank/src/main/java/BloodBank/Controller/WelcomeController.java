package BloodBank.Controller;

import BloodBank.Entity.UserLoginDetailsDTO;
import BloodBank.Entity.UserRequestDTO;
import BloodBank.Entity.UserRequestModel;
import BloodBank.Entity.UserSignUpDetailsDTO;
import BloodBank.Services.BloodInfoServices;
import BloodBank.Services.UserLoginService;
import BloodBank.Services.UserRequestService;
import BloodBank.Services.UserSignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
class WelcomeController {
    @Autowired
    private UserRequestService userRequestService;
    @Autowired
    private UserLoginService userLoginService;
    @Autowired
    private UserSignUpService userSignUpService;
    @Autowired
    private HttpSession httpSession;
    @Autowired
    private BloodInfoServices bloodInfoServices;
    private int loginattempt = 0;

    @GetMapping("/")
    public String welcome() {
        return "welcome";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @PostMapping("/userLoginDetails")
    public String loginUser(UserLoginDetailsDTO userLoginDetailsDTO, Model model, @RequestParam(required = false, defaultValue = "id") String sortBy, @RequestParam(required = false, defaultValue = "") String filterBy) {
        UserSignUpDetailsDTO userSignUpDetailsDTO = userLoginService.authenticateUser(userLoginDetailsDTO.getUsername());
        if (userSignUpDetailsDTO == null) {
            model.addAttribute("ErrorMessage", "Username not exists");
            return "login";
        }
        if (userLoginDetailsDTO.getPassword().equals(userSignUpDetailsDTO.getPassword())) {
            httpSession.setAttribute("loggedInUser", userSignUpDetailsDTO);
            if (userSignUpDetailsDTO.isFirstTimeLogin()) {
                return "updatepassword";
            } else if (userSignUpDetailsDTO.getBlockedStatus()) {
                model.addAttribute("ErrorMessage", "USER BLOCKED");
                return "login";
            } else {
                model.addAttribute("userModel", userSignUpDetailsDTO);
                switch (userSignUpDetailsDTO.getRole()) {
                    case "AD":
                        return "admindashboard";
                    case "EU":
                        return "enduserdashboard";
                    case "AG":
                        return "agentdashboard";
                    default:
                        return "signup";
                }
            }
        } else {
            loginattempt++;
            if (loginattempt < 3) {
                model.addAttribute("ErrorMessage", "Invalid Password");
            } else {
                userLoginService.updateBlockedStatus(true, userSignUpDetailsDTO.getUsername());
                model.addAttribute("ErrorMessage", "You are Blocked");
            }
            return "login";
        }
    }

    @GetMapping({"/sortandfilter", "/usermodel"})
    public String sortedFilteredList(Model model,
                                     @RequestParam(required = false, defaultValue = "id") String sortBy,
                                     @RequestParam(required = false, defaultValue = "") String filterBy,
                                     @RequestParam(required = false) String startDate,
                                     @RequestParam(required = false) String endDate,
                                     @RequestParam(required = false) String username) {

        List<UserSignUpDetailsDTO> userModelList = userLoginService.getAllUsers(sortBy, filterBy, startDate, endDate, username);

        model.addAttribute("userModelList", userModelList);
        return "usermodeltable";
    }

    @PostMapping("/userSignupDetails")
    @ResponseBody
    public String registerUser(@Valid UserSignUpDetailsDTO userSignUpDetailsDTO) {
        userSignUpService.addUser(userSignUpDetailsDTO, "EU", "AUTO");
        return "Successfully registered";
    }

    @PostMapping("/agentSignupDetails")
    @ResponseBody
    public String registerAgent(@Valid UserSignUpDetailsDTO userSignUpDetailsDTO) {
        userSignUpService.addUser(userSignUpDetailsDTO, "AG", "ADMIN");
        return "Successfully Registered";
    }

    @GetMapping("/addagent")
    public String addAgent() {
        return "agentsignup";
    }

    @GetMapping("/addenduser")
    public String addUserByAgent() {
        return "usersignupbyagent";
    }

    @PostMapping("/userSignupDetailsByAgent")
    @ResponseBody
    public String registerUserByAgent(@Valid UserSignUpDetailsDTO userSignUpDetailsDTO) {
        userSignUpService.addUser(userSignUpDetailsDTO, "EU", ((UserSignUpDetailsDTO) httpSession.getAttribute("loggedInUser")).getUsername());
        return "Successfully registered";
    }

    @PostMapping("/userFirstLogin")
    public String updatePassword(@RequestParam String username,
                                 @RequestParam String newPassword) {
        userLoginService.updateUserPassword(newPassword, username);
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        httpSession.invalidate();
        return "welcome";
    }

    @GetMapping("/makerequest")
    public String takeRequest() {
        return "request";
    }

    @PostMapping("/userBloodRequest")
    @ResponseBody
    public String requested(UserRequestDTO userRequestDTO) {
        if (userRequestDTO.getType().equals("Donate")) {
            if (!userRequestService.canDonate(((UserSignUpDetailsDTO) httpSession.getAttribute("loggedInUser")).getUsername(),userRequestDTO.getBloodGroup())){
                return "You can only donate after three months of previous donation";
            }
        }
        userRequestService.addRequest(userRequestDTO);
        return "Request Registered";
    }

    @GetMapping("/seerequest")
    public String seeRequests(Model model) {
        model.addAttribute("userRequestList", userRequestService.getAllRequest());
        return "requesttable";
    }

    @GetMapping({"/sortby", "/agentuser"})
    public String sortAgentUser(Model model, @RequestParam(required = false, defaultValue = "id") String sortBy) {
        List<UserSignUpDetailsDTO> agentuserList = userLoginService.getAllUsersByAgent(((UserSignUpDetailsDTO) httpSession.getAttribute("loggedInUser")).getUsername(), sortBy);
        model.addAttribute("userModelList", agentuserList);
        return "agentusertable";
    }

    @PostMapping("/statusupdate")
    public String updateStatus(@RequestParam String reqId, @RequestParam String status, @RequestParam(required = false) String remark, Model model) {
        Long ReqId = Long.parseLong(reqId);
        userRequestService.updateRequestStatus(status, ReqId);
        model.addAttribute("userRequestList", userRequestService.getAllRequest());
        if ("accepted".equals(status)) {
            userLoginService.coinupdate(ReqId);
            bloodInfoServices.updateStock(ReqId);
        } else if ("rejected".equals(status)) {
            if (remark != null && !remark.isEmpty()) {
                userRequestService.updateRequestRemark(ReqId, remark);
            }
        }
        return "requesttable";
    }

    @GetMapping("/seestock")
    @ResponseBody
    public String seebloodstock() {
        return bloodInfoServices.bloodstock().toString();
    }

    @GetMapping("/seemyrequests")
    public String seeRequest(Model model) {
        String role = ((UserSignUpDetailsDTO) httpSession.getAttribute("loggedInUser")).getRole();
        String username1 = ((UserSignUpDetailsDTO) httpSession.getAttribute("loggedInUser")).getUsername();
        List<UserRequestModel> requests;
        if (role.equals("AG")) {
            requests = userRequestService.getMyUserRequest(username1);
            model.addAttribute("userRequestList", requests);
            return "userrequest";
        } else {
            requests = userRequestService.getMyRequest(username1);
            model.addAttribute("userRequestList", requests);
            return "myrequest";
        }
    }

    @GetMapping("/seebloodreport")
    public String seeRequestReport(Model model, @RequestParam String username) {
        model.addAttribute("userRequestReport", userRequestService.requestreport(username));
        return "requestreports";
    }

    @GetMapping("filter")
    public String filterList(Model model, @RequestParam(required = false, defaultValue = "") String filterBy,
                             @RequestParam(required = false) String startDate,
                             @RequestParam(required = false) String endDate,
                             @RequestParam(required = false) String ReqId,
                             @RequestParam(required = false) String username,
                             @RequestParam(required = false) String statusFilter) {
        model.addAttribute("userRequestList", userRequestService.getFilterRequests(filterBy, startDate, endDate, username, statusFilter, userRequestService.getAllRequest()));
        return "requesttable";
    }

    @GetMapping("/coinreport")
    public String seeCoinReport(Model model, @RequestParam String username, @RequestParam String role) {
        if (role.equals("AG")) {
            model.addAttribute("CoinReport", userLoginService.getCoinReportAgent(username));
            return "coinreportonagent";
        } else {
            model.addAttribute("CoinReport", userLoginService.getCoinReportAdmin());
            return "coinreportonadmin";
        }
    }
    @GetMapping("filterlist")
    public String agentUserfilterList(Model model, @RequestParam(required = false, defaultValue = "") String filterBy,
                             @RequestParam(required = false) String startDate,
                             @RequestParam(required = false) String endDate,
                             @RequestParam(required = false) String ReqId,
                             @RequestParam(required = false) String username,
                             @RequestParam(required = false) String statusFilter) {

        String role=((UserSignUpDetailsDTO) httpSession.getAttribute("loggedInUser")).getRole();
        String username1=((UserSignUpDetailsDTO) httpSession.getAttribute("loggedInUser")).getUsername();
        if(role.equalsIgnoreCase("AG")) {
            model.addAttribute("userRequestList", userRequestService.getFilterRequestsAgentUser(filterBy, startDate, endDate, username, statusFilter, userRequestService.getMyUserRequest(username1)));
            return "userrequest";
        }
        model.addAttribute("userRequestList", userRequestService.getFilterRequestsAgentUser(filterBy, startDate, endDate, username, statusFilter, userRequestService.getMyRequest(username1)));
        return "myrequest";
    }
}
