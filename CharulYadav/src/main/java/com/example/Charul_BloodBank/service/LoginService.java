package com.example.Charul_BloodBank.service;

import com.example.Charul_BloodBank.dto.UserLoginDTO;
import com.example.Charul_BloodBank.model.UserModel;
import com.example.Charul_BloodBank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    int failedAttempts = 0;
    @Autowired
    private UserRepository bloodBankRepository;
    public boolean authenticate(UserLoginDTO userLoginDto) {
        UserModel user = bloodBankRepository.findByUsernameAndPassword(userLoginDto.getUsername(), userLoginDto.getPassword());
        if(user!=null && user.getPassword().equals(userLoginDto.getPassword()))
            return true;
        else {
            failedAttempts++;
            if(failedAttempts >= 3)
            {
                bloodBankRepository.updateLockedByUsername(true, userLoginDto.getUsername());
            }
            return false;
        }
    }
    public UserModel getUser(String username) {
        return bloodBankRepository.findByUsername(username);
    }
    public void setFirstLoginUpdate(String username)
    {
        UserModel user = bloodBankRepository.findByUsername(username);
        bloodBankRepository.updateFirstLogin(false, username);
    }
    public void updateUserPassword(String password, String username)
    {
        bloodBankRepository.updatePassword(password, username);
        setFirstLoginUpdate(username);
    }
}
