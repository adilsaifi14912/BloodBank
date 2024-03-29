package com.example.service;

import com.example.model.UserModel;
import com.example.repository.DatabaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class AdminService {
    @Autowired
    DatabaseRepository databaseRepository;

    public void setAdmin() {
        UserModel admin = new UserModel();
        admin.setUserName("Admin");
        admin.setName("Admin....");
        admin.setDob(Date.valueOf("2000-01-01"));
        admin.setBloodGroup("B+");
        admin.setPassword("admin123");
        admin.setCreatedOn();
        admin.setCreatedBy("Self");
        admin.setRole("ADMIN");
        Iterable<UserModel> users = databaseRepository.findAll();
        if (!users.iterator().hasNext()) {
            databaseRepository.save(admin);
        }
        else {
            System.out.println("not empty");
        }
    }
}
