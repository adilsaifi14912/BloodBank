package com.example.Charul_BloodBank.service;

import com.example.Charul_BloodBank.dto.UserLoginDTO;
import com.example.Charul_BloodBank.model.UserModel;
import com.example.Charul_BloodBank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private UserRepository bloodBankRepository;
    public boolean authenticate(UserLoginDTO userLoginDto) {
        UserModel user = bloodBankRepository.findByUsernameAndPassword(userLoginDto.getUsername(), userLoginDto.getPassword());
        return user != null;
    }
    public UserModel getUser(String username) {
        return bloodBankRepository.findByUsername(username);
    }
}
