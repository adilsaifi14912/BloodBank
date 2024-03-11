package com.example.service;

import com.example.dto.UserLoginDto;
import com.example.dto.UserRegisterDto;
import com.example.model.UserModel;
import com.example.repository.DatabaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    DatabaseRepository databaseRepository;

    //1st approach
    public boolean checkUser(UserLoginDto userLoginDto) {
        Iterable<UserModel> users = databaseRepository.findAll();
        for (UserModel userModel : users) {
            if ((userModel.getUsername().equals(userLoginDto.getUserName())) && (userModel.getPassword().equals(userLoginDto.getPassword()))) {
                return true;
            }
        }
        return false;
    }

    //2nd approach
//    public boolean checkUser(UserLoginDto userLoginDto) {
//        UserModel user = databaseRepository.checkForLogin(userLoginDto.getUserName(), userLoginDto.getPassword());
//        return (user != null && userLoginDto.getUserName().equals(user.getUsername()));
//    }
}
