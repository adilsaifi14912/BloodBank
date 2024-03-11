package com.BloodBank.Service;

import com.BloodBank.Model.User;
import com.BloodBank.Repository.UserRepository;
import com.BloodBank.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;

public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void addUser(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setName(userDTO.getName());
        user.setPhone(userDTO.getPhone());
        user.setDob(userDTO.getDob());
        userRepository.save(user);

    }

}
