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
public class WelcomeController {
    @Autowired
    private UserLoginService userLoginService;
    @Autowired
    private UserSignUpService userSignUpService;
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
        UserModel userModel = userLoginService.authenticateUser(userLoginDetailsDTO.getUsername(), userLoginDetailsDTO.getPassword());
        if (userModel != null) {
            if(userModel.isFirstTimeLogin()){
                userLoginService.updateNewUserLogin(false, userLoginDetailsDTO.getUsername());
                return "firstlogin";}
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
                    return "login";
            }
        } else {
            model.addAttribute("ErrorMessage","Invalid Username or Password");
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
                                 @RequestParam String newPassword){
        userLoginService.updateUserPassword(newPassword,username);


        return "login";
    }
}
