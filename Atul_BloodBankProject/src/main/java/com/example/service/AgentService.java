package com.example.service;
import com.example.model.UserModel;
import com.example.repository.DatabaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
@Service
public class AgentService {
    @Autowired
    DatabaseRepository databaseRepository;
    public void setAgent() {
        UserModel agent = new UserModel();
        agent.setUserName("Agent");
        agent.setName("Agent ....");
        agent.setDob(Date.valueOf("2001-01-01"));
        agent.setBloodGroup("O+");
        agent.setPassword("agent123");
        agent.setCreatedOn();
        agent.setCreatedBy("Admin");
        agent.setRole("AGENT");
        Iterable<UserModel> users = databaseRepository.findAll();
        int f = 1;
        for (UserModel userModel : users) {
            if (userModel.getUserName().equals(agent.getUserName())) {
                f = 0;
            }
        }
        if (f == 1) {
            System.out.println("set agent");
            databaseRepository.save(agent);
        }

    }
}
