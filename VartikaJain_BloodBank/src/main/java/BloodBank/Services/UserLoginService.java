package BloodBank.Services;

import BloodBank.Entity.UserModel;
import BloodBank.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserLoginService {

    @Autowired
    private UserRepository userRepository;

    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    public void updateUserPassword(String newPassword, String username) {
        userRepository.updatePasswordByUsername(newPassword, username);
        updateNewUserLogin(false,username);
    }

    public void  updateNewUserLogin(boolean firstTimeLogin, String username){
        userRepository.updateFirstTimeLoginByUsername(firstTimeLogin, username);
    }

    public void updateBlockedStatus(boolean blockedStatus,String username){
        userRepository.updateUserBlockedStatusByUsername(blockedStatus,username);
    }

    public UserModel authenticateUser(String username) {

        return userRepository.findByUsername(username);
    }
}

