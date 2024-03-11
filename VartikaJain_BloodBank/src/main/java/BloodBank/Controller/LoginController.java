package BloodBank.Controller;

import BloodBank.Entity.UserLoginDetails;
import BloodBank.Services.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private UserLoginService userLoginService;
    @PostMapping("/userLoginDetails")
    public String loginUser(@RequestBody UserLoginDetails userLoginDetails){
            boolean isAuthenticated = userLoginService.authenticateUser(userLoginDetails.getUsername(),userLoginDetails.getPassword(),userLoginDetails.getContactNumber());
            if (isAuthenticated) {
                return "Login successful!";
            } else {
                return "Invalid username, password, or phone number.";
            }
    }
}
