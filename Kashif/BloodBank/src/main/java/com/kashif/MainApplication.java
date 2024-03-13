package com.kashif;
import com.kashif.entity.UserRegistration;
import com.kashif.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.Date;

@SpringBootApplication
public class MainApplication implements CommandLineRunner {

	@Autowired
	private UserRepo userRepo;

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}

	@Override
	public void run(String... args) {
		if (!userRepo.existsByRole("admin")) {
			UserRegistration userRegistration = new UserRegistration();
			userRegistration.setName("Admin-Kashif");
			userRegistration.setEmail("kashifnezam123@gmail.com");
			userRegistration.setPassword("123");
			userRegistration.setRole("admin");
			userRegistration.setUsername("admin");
			userRegistration.setNewUser(false);
			userRegistration.setBloodGroup("O+");
			userRegistration.setBlockedStatus(false);
			userRegistration.setAddress("Bihar");
			userRegistration.setModifyBy("-");
			userRegistration.setCreatedBy("-");
			userRegistration.setCreationTime(new Date());
			userRegistration.setDob(LocalDate.parse("2000-11-02"));
			userRegistration.setUpdatedTime(new Date());

			userRepo.save(userRegistration);
		}
	}
}
