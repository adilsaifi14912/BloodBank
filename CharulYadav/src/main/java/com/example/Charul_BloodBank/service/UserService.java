package com.example.Charul_BloodBank.service;

import com.example.Charul_BloodBank.dto.UserDTO;
import com.example.Charul_BloodBank.model.User;
import com.example.Charul_BloodBank.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void saveUser(UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        userRepository.save(user);
    }
}

