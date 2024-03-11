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

    public boolean checkUser(UserLoginDto userLoginDto) {
        Iterable<UserModel> users = databaseRepository.findAll();
        for (UserModel userModel : users) {
            if ((userModel.getUserName().equals(userLoginDto.getUserName())) && (userModel.getPassword().equals(userLoginDto.getPassword()))) {
                return true;
            }
        }
        return false;
    }
}
