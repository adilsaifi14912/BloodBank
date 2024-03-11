package BloodBank.Services;

import BloodBank.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import BloodBank.Entity.UserModel;
import BloodBank.Entity.UserSignUpDetails;

@Service
public class UserSignUpService {

    @Autowired
    private UserRepository userRepository;

    public void addUser(UserSignUpDetails userSignUpDetails) {
        UserModel user = new UserModel();
        user.setUsername(userSignUpDetails.getUsername());
        user.setDob(userSignUpDetails.getDob());
        user.setContactNumber(userSignUpDetails.getContactNumber());
        user.setAddress(userSignUpDetails.getAddress());
        user.setPassword(userSignUpDetails.getPassword());
        userRepository.save(user);
    }
}
