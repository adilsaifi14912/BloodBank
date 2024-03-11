package com.example;

import com.example.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AtulBloodBankProjectApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(AtulBloodBankProjectApplication.class, args);
        AdminService bean = context.getBean(AdminService.class);
        bean.setAdmin();  //for set admin at application starting


    }

}
