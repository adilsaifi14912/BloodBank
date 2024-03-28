package com.example.Charul_BloodBank.service;

import com.example.Charul_BloodBank.dto.UserSignUpDTO;
import com.example.Charul_BloodBank.model.UserModel;
import com.example.Charul_BloodBank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SignUpService {
    @Autowired
    private UserRepository bloodBankRepository;
    public void insert(UserSignUpDTO userDto) {
        UserModel user1 = new UserModel();
        user1.setName(userDto.getName());
        user1.setUsername(userDto.getUsername());
        user1.setPassword(userDto.getPassword());
        user1.setDob(userDto.getDob());
        user1.setCreatedOn();
        user1.setRole("enduser");
        user1.setCreatedBy();
        user1.setBloodGroup(userDto.getBloodGroup());
        user1.setFirstLogin(true);
        bloodBankRepository.save(user1);
    }
    public List<UserModel> getAllUsers() {
        return bloodBankRepository.findAll();
    }
}
