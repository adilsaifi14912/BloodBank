package com.example;

import com.example.service.AdminService;
import com.example.service.AgentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AtulBloodBankProjectApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(AtulBloodBankProjectApplication.class, args);
        AdminService adminService = context.getBean(AdminService.class);
        AgentService agentService=context.getBean(AgentService.class);
        adminService.setAdmin();  //for set admin at application starting
        agentService.setAgent();  //for set agent at application starting


    }
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
