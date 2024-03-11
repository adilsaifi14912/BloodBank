package com.example;

import com.example.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ShivamBloodBankApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext run = SpringApplication.run(ShivamBloodBankApplication.class, args);

        AdminService admin = run.getBean(AdminService.class);
        admin.setAdmin();
    }

}
