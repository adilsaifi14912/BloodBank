package com.BloodBank.Controller;
import com.BloodBank.Model.UserModel;
import com.BloodBank.Service.UserService;
import com.BloodBank.dto.UserLoginDTO;
import com.BloodBank.dto.UserSignUpDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
public class LoginSignUpController {

    private int loginAttempt =0;
    @Autowired
    private UserService userService;

    @PostMapping("/addData")
    public String addData( UserSignUpDTO userSignUpDTO)
    {
        userService.addUser(userSignUpDTO);
        return "redirect:/login";
    }

    @PostMapping("/loginUser")
    public String loginUser(UserLoginDTO userLoginDTO, Model model) {
        UserModel userModel=userService.authenticateUser(userLoginDTO.getUsername());
        if(userModel==null){
            model.addAttribute("errorMsg", "Username not exists");
            return "login";
        }
        if(userLoginDTO.getPassword().equals(userModel.getPassword())){
            if (userModel.isFirstTimeLogin()) {
                return "firstLogin";
            } else if (userModel.getBlockedStatus()) {
                model.addAttribute("errorMsg", "USER BLOCKED");
                return "login";
            } else{
                model.addAttribute("userModel", userModel);
                switch (userModel.getRole()) {
                    case "AD":
                        List<UserModel> userModelList = userService.getAllUsers();
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

    @PostMapping("/userFirstLogin")
    public String updatePassword(@RequestParam String username,
                                 @RequestParam String newPassword) {
        userService.updateUserPassword(newPassword, username);
        return "login";
    }

}
