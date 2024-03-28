package com.BloodBank.Service;

import com.BloodBank.Model.UserModel;
import com.BloodBank.Repository.UserRepository;
import com.BloodBank.dto.UserSignUpDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    public void addUser(UserSignUpDTO userSignUpDTO) {
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userSignUpDTO, userModel);
        userModel.setCreated_date_time(LocalDateTime.now());
        userModel.setCreatedBy("auto");
        userModel.setRole("EU");
        userModel.setModifyBy("self");
        userModel.setUpdated_date_time(LocalDateTime.now());
        userRepository.save(userModel);
    }

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
