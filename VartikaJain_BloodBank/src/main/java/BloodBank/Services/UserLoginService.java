package BloodBank.Services;

import BloodBank.Entity.UserModel;
import BloodBank.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserLoginService {

    @Autowired
    private UserRepository userRepository;

    public UserModel authenticateUser(String username, String password) {
        UserModel userModel = userRepository.findByUsernameAndPassword(username, password);
        return userModel;
    }

    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    public void updateUserPassword(String newPassword, String username) {
        userRepository.updatePasswordByUsername(newPassword, username);
    }

    public void  updateNewUserLogin(Boolean FirstTimeLogin, String username){
        userRepository.updateFirstTimeLoginByUsername(FirstTimeLogin, username);
    }


}

