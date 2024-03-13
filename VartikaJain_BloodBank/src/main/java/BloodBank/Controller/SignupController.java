package BloodBank.Controller;

import BloodBank.Entity.UserSignUpDetails;
import BloodBank.Services.UserSignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignupController {
    @Autowired
    private UserSignUpService userSignUpService;
    @PostMapping("/userSignupDetails")
    public String registerUser(@RequestBody UserSignUpDetails userSignUpDetails) {
        userSignUpService.addUser(userSignUpDetails);
        return "Successfully registered";
    }
}
