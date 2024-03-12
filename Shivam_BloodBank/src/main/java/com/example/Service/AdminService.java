package com.example.Service;

import com.example.Model.UserModel;
import com.example.Repository.BloodBankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class AdminService {
    @Autowired
    private BloodBankRepository bloodBankRepository;

    public void setAdmin() {
        UserModel admin = new UserModel();
        admin.setName("Admin");
        admin.setDob(Date.valueOf("2000-03-01"));
        admin.setRole("Admin");
        admin.setBloodGroup("B+");
        admin.setCreatedOn();
        admin.setCreatedBy("self");
        admin.setUsername("Admin_1");
        admin.setPassword("admin123");
        Iterable<UserModel> user1 = bloodBankRepository.findAll();
        if (!(user1.iterator().hasNext())) {
            System.out.println("in admin service method");
            bloodBankRepository.save(admin);
        }
    }
}

