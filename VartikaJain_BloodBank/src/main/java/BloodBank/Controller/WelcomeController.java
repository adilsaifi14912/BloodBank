package BloodBank.Controller;

import BloodBank.Entity.UserLoginDetailsDTO;
import BloodBank.Entity.UserModel;
import BloodBank.Entity.UserSignUpDetailsDTO;
import BloodBank.Services.UserLoginService;
import BloodBank.Services.UserSignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
class WelcomeController {
    @Autowired
    private UserLoginService userLoginService;
    @Autowired
    private UserSignUpService userSignUpService;
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
    public String loginUser(UserLoginDetailsDTO userLoginDetailsDTO, Model model) {
        UserModel userModel=userLoginService.authenticateUser(userLoginDetailsDTO.getUsername());
        if(userModel==null){
            model.addAttribute("ErrorMessage", "Username not exists");
            return "login";
        }
        if(userLoginDetailsDTO.getPassword().equals(userModel.getPassword())){
            if (userModel.isFirstTimeLogin()) {
                return "firstlogin";
            } else if (userModel.getBlockedStatus()) {
                model.addAttribute("ErrorMessage", "USER BLOCKED");
                return "login";
            } else{
                model.addAttribute("userModel", userModel);
                switch (userModel.getRole()) {
                    case "AD":
                        List<UserModel> userModelList = userLoginService.getAllUsers();
                        model.addAttribute("userModelList", userModelList);
                        return "admindashboard";
                    case "EU":
                        return "enduserdashboard";
                    case "AG":
                        return "agentdashboard";
                    default:
                        return "signup";
                }
            }
        }
        else {
            loginattempt++;
            if (loginattempt < 3) {
                model.addAttribute("ErrorMessage", "Invalid Password");
            } else {
                userLoginService.updateBlockedStatus(true,userModel.getUsername());
                model.addAttribute("ErrorMessage", "You are Blocked");
            }
            return "login";
        }
    }
    @PostMapping("/userSignupDetails")
    @ResponseBody
    public String registerUser(@Valid UserSignUpDetailsDTO userSignUpDetailsDTO) {
        userSignUpService.addUser(userSignUpDetailsDTO);
        return "Successfully registered";
    }
    @PostMapping("/userFirstLogin")
    public String updatePassword(@RequestParam String username,
                                 @RequestParam String newPassword) {
        userLoginService.updateUserPassword(newPassword, username);
        return "login";
    }
    @PostMapping("/logout")
    public String logout() {
        return "welcome";
    }
}
