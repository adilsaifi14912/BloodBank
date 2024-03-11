package com.example.Service;

import com.example.Dto.UserLoginDto;
import com.example.Model.UserModel;
import com.example.Repository.BloodBankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLoginService {
    @Autowired
    private BloodBankRepository bloodBankRepository;
    //check for user exist or not
    //1st Approach

    //    public boolean check(UserLoginDto userLoginDto) {
//        System.out.println("inside check");
//        Iterable<UserModel> allFetchUser = userRepository.findAll();
//        for(UserModel userModel:allFetchUser){
//            if((userLoginDto.getUsername().equalsIgnoreCase(userModel.getUsername()))
//                    &&(userLoginDto.getPassword().equals(userModel.getPassword()))){
//                return true;
//            }
    //2nd Approach
    public boolean check(UserLoginDto userLoginDto) {
        UserModel user = bloodBankRepository.checkForLogin(userLoginDto.getUsername(), userLoginDto.getPassword());
        return (user != null && userLoginDto.getUsername().equals(user.getUsername()));
    }
}
