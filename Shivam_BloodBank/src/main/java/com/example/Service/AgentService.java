package com.example.Service;

import com.example.Model.UserModel;
import com.example.Repository.BloodBankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class AgentService {
    @Autowired
    private BloodBankRepository bloodBankRepository;

    public void setAgent() {
        System.out.println("In agent service");
        UserModel agent = new UserModel();
        agent.setName("Agent");
        agent.setDob(Date.valueOf("2002-05-05"));
        agent.setRole("Agent");
        agent.setCreatedBy("admin");
        agent.setCreatedOn();
        agent.setBloodGroup("A+");
        agent.setUsername("Agent_1");
        agent.setPassword("agent123");
        Iterable<UserModel> user1 = bloodBankRepository.findAll();
        boolean flag=true;
        for(UserModel user:user1){
            if(user.getUsername().equals(agent.getUsername())){
                flag=false;
            }
        }
        if(flag)
            bloodBankRepository.save(agent);
    }
}
