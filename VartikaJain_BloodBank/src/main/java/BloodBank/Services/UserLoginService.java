package BloodBank.Services;

import BloodBank.Entity.UserModel;
import BloodBank.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLoginService {

    @Autowired
    private UserRepository userRepository;

    public boolean authenticateUser(String username, String password, String contactNumber) {
        UserModel userModel = userRepository.findByUsernameAndPasswordAndContactNumber(username, password, contactNumber);
        return userModel != null;
    }

}
