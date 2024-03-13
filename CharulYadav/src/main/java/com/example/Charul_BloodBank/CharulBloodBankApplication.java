package com.example.Charul_BloodBank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.Charul_BloodBank")
public class CharulBloodBankApplication {
	public static void main(String[] args) {
		SpringApplication.run(CharulBloodBankApplication.class, args);

	}
}
