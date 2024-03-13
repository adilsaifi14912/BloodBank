package BloodBank.Aspects;

import BloodBank.Entity.UserSignUpDetails;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserSignUpAspects {

    @Before("execution(* BloodBank.Services.UserSignUpService.addUser(..)) && args(userSignUpDetails)")
    public void validateSignup(UserSignUpDetails userSignUpDetails) {
        if (userSignUpDetails.getUsername() == null || userSignUpDetails.getUsername().isEmpty()
                || userSignUpDetails.getDob() == null
                || userSignUpDetails.getContactNumber() == null || userSignUpDetails.getContactNumber().isEmpty()
                || userSignUpDetails.getAddress() == null || userSignUpDetails.getAddress().isEmpty()) {
            throw new IllegalArgumentException("All fields are required for signup");
        } else if (userSignUpDetails.getPassword() == null || userSignUpDetails.getPassword().isEmpty()) {
            userSignUpDetails.setPassword("12345");
            System.out.println("Your default password is 12345");
        }
    }
}
