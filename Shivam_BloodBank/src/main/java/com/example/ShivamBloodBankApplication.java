package com.example;

import com.example.Service.AdminService;
import com.example.Service.AgentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ShivamBloodBankApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext run = SpringApplication.run(ShivamBloodBankApplication.class, args);

        AdminService admin = run.getBean(AdminService.class);
        AgentService agent=run.getBean(AgentService.class);
        admin.setAdmin();   //setting admin at the time of table creation
        agent.setAgent();   //setting agent at the time of table creation

    }
    @Bean
    public ModelMapper getModelInstance(){
        return new ModelMapper();
    }

}
