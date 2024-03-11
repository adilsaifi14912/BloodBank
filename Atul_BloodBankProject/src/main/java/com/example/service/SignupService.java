package com.example.service;

import com.example.dto.UserRegisterDto;
import com.example.model.UserModel;
import com.example.repository.DatabaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignupService {
    @Autowired
    DatabaseRepository databaseRepository;

    public Boolean addUser(UserRegisterDto userRegisterDto) {
        Iterable<UserModel> users = databaseRepository.findAll();
        UserModel userModel = new UserModel();
        for (UserModel userModel1 : users) {
            if (userModel1.getUserName().equals(userRegisterDto.getUserName())) {
                return true;
            }
        }
        userModel.setUserName(userRegisterDto.getUserName());
        userModel.setName(userRegisterDto.getName());
        userModel.setCreatedOn();
        userModel.setPassword(userRegisterDto.getPassword());
        userModel.setDob(userRegisterDto.getDob());
        databaseRepository.save(userModel);

        return false;
    }

}
