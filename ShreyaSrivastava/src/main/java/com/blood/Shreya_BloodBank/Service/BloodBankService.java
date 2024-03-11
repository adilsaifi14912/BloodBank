package com.blood.Shreya_BloodBank.Service;

import com.blood.Shreya_BloodBank.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BloodBankService {
        @Autowired
        private UserRepository userRepository;

        public void createTable()
        {
            userRepository.createTable();
        }

        public void addUser(String username, String password, String name, Long phone) {
            userRepository.saveUser(username, password, name, phone);
        }
}
