package com.blood.Shreya_BloodBank.Service;

import com.blood.Shreya_BloodBank.Entity.UserLoginDetails;
import com.blood.Shreya_BloodBank.Entity.UserModel;
import com.blood.Shreya_BloodBank.Entity.UserSignUpDetails;
import com.blood.Shreya_BloodBank.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BloodBankService {
        @Autowired
        private UserRepository userRepository;

        public void addUser(UserSignUpDetails userSignUpDetails) {
            UserModel user =new UserModel();
            user.setUsername(userSignUpDetails.getUsername());
            user.setDob(userSignUpDetails.getDob());
            user.setPhone(userSignUpDetails.getPhone());
            user.setAddress((userSignUpDetails.getAddress()));
            user.setPassword(userSignUpDetails.getPassword());
            user.setBloodGroup(userSignUpDetails.getBloodGroup());
            user.setRole(userSignUpDetails.getRole());
            user.setChoice(userSignUpDetails.getChoice());
            //user.setId(1L);
            user.setCreatedby("EndUser");
            user.setCreatedOn(LocalDateTime.now());
            userRepository.save(user);
            //System.out.println(save);

            //user.setFirstLogin(true); // Set first login to true initially
            userRepository.save(user);
        }

//    public boolean isFirstLogin(String username) {
//        UserModel user = userRepository.findByUsername(username);
//        return user != null && user.isFirstLogin();
//    }

    public boolean authenticateUser(UserLoginDetails userLoginDetails) {
        UserModel userModel = userRepository.findByUsernameAndPassword(userLoginDetails.getUsername(),userLoginDetails.getPassword());
        return userModel != null;
    }

    public UserModel getUser(String username){
            return userRepository.findByUsername(username);
    }

    public List<UserModel> getAllUsers(){
            return userRepository.findAll();

    }

}
