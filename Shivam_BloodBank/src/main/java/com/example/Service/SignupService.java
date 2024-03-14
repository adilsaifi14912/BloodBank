package com.example.Service;

import com.example.Dto.UserSignupDto;
import com.example.Model.UserModel;
import com.example.Repository.BloodBankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignupService {
    @Autowired
    private BloodBankRepository bloodBankRepository;
    //1st Approach
//    public boolean insert(UserSignupDto userDto){
//         check whether username is already exist or not
//        Iterable<UserModel> fetchUsers = userRepository.findAll();
//        for(UserModel userModel:fetchUsers){
//            if(userDto.getUsername().equals(userModel.getUsername()))
//                return false;
//        }

    //2nd Approach
    public boolean insert(UserSignupDto userDto) {
        //check whether username is already exist or not
        UserModel user = bloodBankRepository.checkForSignup(userDto.getUsername());
        if (user != null && (userDto.getUsername().equals(user.getUsername()))) {
            return false;
        }
        UserModel user1 = new UserModel();
        //setting values to the parameters of userDto class
        user1.setName(userDto.getName());
        user1.setUsername(userDto.getUsername());
        user1.setBloodGroup(userDto.getBloodGroup());
        user1.setPassword(userDto.getPassword());
        user1.setDob(userDto.getDob());
        user1.setCreatedOn();
        user1.setRole("Enduser");
        user1.setCreatedBy("admin");
        UserModel save = bloodBankRepository.save(user1);
        return true;
    }

}


